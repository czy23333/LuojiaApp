/**
 * 负责与服务器之间的的信息传递
 * by MMMMMMoSky
 */
package com.example.luojiaapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerConnection {
    //    private static String serverIP = "10.131.168.166";
//    private static String serverIP = "10.131.138.53";
    private static String serverIP = "192.168.2.129";
    private static int serverPort = 12345;

    /**
     * 登陆验证 传入用户id和密码, 若登陆成功, 返回用户名, 若登陆失败, 返回 null
     *
     * @param userid
     * @param password
     * @return 用户名
     */
    public static String verifyPassword(final String userid, final String password) {
        final List<String> ret = new ArrayList<>();  // 黑科技传递消息? 不想用 Handler
        Runnable vp = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    out.write("verify password".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(userid.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(password.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    String username = (new String(buf, "utf-8")).trim();
                    if (username != null && username.indexOf("?") < 0) {  // 要求用户名不含问号
                        ret.clear();
                        ret.add(username);
                    }
                } catch (Exception e) {
                    ret.clear();
                }
            }
        };

        Thread thread = new Thread(vp);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            ret.clear();
        }

        if (ret.size() != 1 || "?".equals(ret.get(0))) {
            return null;
        }

        return ret.get(0);
    }

    /**
     * 注册用户 提供用户昵称和密码, 返回用户id, 注册失败则返回 null
     *
     * @param username
     * @param password
     * @return 用户id
     */
    public static String registerCommonUser(final String username, final String password) {
        final List<String> ret = new ArrayList<>();
        Runnable rc = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    out.write("register common user".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(username.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(password.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    String userid = (new String(buf, "utf-8")).trim();

                    if (userid != null && userid.indexOf("?") < 0) {  // 要求用户名不含问号
                        ret.clear();
                        ret.add(userid);
                    }
                } catch (Exception e) {
                    ret.clear();
                }
            }
        };

        Thread thread = new Thread(rc);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            ret.clear();
        }

        if (ret.size() != 1 || "?".equals(ret.get(0))) {
            return null;
        }

        return ret.get(0);
    }

    /**
     * 获取当前用户的商人信息, 若该用户还未注册成为商人, 返回 null
     * 发送的是已经登陆的用户id, 这个方法不应该在未登陆的时候调用
     *
     * @return 商人信息 "%s;%s;%s;%s;%s" % (姓名, 联系方式, 学部, 院系, 年级)
     */
    public static String getSellerInfo() {
        if (!LoginStatus.loggedin) {
            return null;
        }

        final List<String> ret = new ArrayList<>();
        Runnable gs = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    out.write("get seller info".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(LoginStatus.userid.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    String info = (new String(buf, "utf-8")).trim();
                    if (info != null && info.indexOf("?") < 0) {  // 要求用户名不含问号
                        ret.clear();
                        ret.add(info);
                    }
                } catch (Exception e) {
                    ret.clear();
                }
            }
        };

        Thread thread = new Thread(gs);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            ret.clear();
        }

        if (ret.size() != 1 || "?".equals(ret.get(0))) {
            return null;
        }

        return ret.get(0);
    }

    /**
     * 注册成为商人
     * 发送的是已经登陆的用户id, 这个方法不应该在未登陆的时候调用
     *
     * @param sellerinfo 注册信息, 格式: "%s;%s;%s;%s;%s" % (姓名, 联系方式, 学部, 院系, 年级)
     * @return 注册是否成功
     */
    public static boolean registerSeller(final String sellerinfo) {
        final List<Boolean> ret = new ArrayList<>();
        Runnable rs = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    out.write("register seller".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(LoginStatus.userid.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    out.write(sellerinfo.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    String res = (new String(buf, "utf-8")).trim();
                    ret.clear();
                    ret.add("true".equalsIgnoreCase(res));
                } catch (Exception e) {
                    ret.clear();
                }
            }
        };

        Thread thread = new Thread(rs);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            ret.clear();
        }

        if (ret.size() != 1) {
            return false;
        }

        return ret.get(0);
    }

    /**
     * 卖家上传一个新的商品
     *
     * @param item 商品, 具体内容见 Item.java, 需要将其所有的信息上传至服务器
     */
    public static boolean sellerAddItem(final Item item) {
        final List<Boolean> ret = new ArrayList<>();
        Runnable sai = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    // 0. 发送控制信息: seller add item
                    out.write("seller add item".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 1. 发送 userid, 表示商品属主
                    out.write(LoginStatus.userid.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 2. 发送商品基本信息, 字符串格式为 "%s;%s;%s;%s"
                    //  表示 商品名称;商品价格;商品数量;商品类型编号
                    String info = item.getName() + ";" + item.getPrice() + ";" +
                            item.getAmount() + ";" + item.getCategory();
                    out.write(info.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 将商品图片 Bitmap 转换成 byte[] 数组
                    byte[] pic = PicProcess.bitmap2bytes(item.getBitmap());
                    // 3. 发送商品图片的字节长度
                    out.write(String.valueOf(pic.length).getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 4. 分批发送商品图片, 每次发送 1024 bytes, 最后一次可能不足 1024 bytes, 可以根据上一次发送的数字计算
                    for (int i = 0; i < pic.length; i += 1024) {
                        out.write(pic, i, Math.min(1024, pic.length - i));
                        Arrays.fill(buf, (byte) 0);
                        in.read(buf);
                    }

                    // 5. 最后一次读取到的内容应该为 true, 表示该商品已经添加成功
                    String res = (new String(buf, "utf-8")).trim();
                    ret.add("true".equals(res));
                } catch (Exception e) {
                }
            }
        };

        Thread thread = new Thread(sai);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
        }

        return ret.size() == 1 && ret.get(0);
    }

    private static Item getAnItem(DataInputStream in, DataOutputStream out, byte[] buf) throws Exception {
        // 这一条信息其实没有意义, 本身轮到 client 发送一条消息了而已
        out.write("get an item".getBytes("utf-8"));

        // 服务器发送商品基本信息, "%s;%s;%s;%s" 商品名称;商品价格;商品数量;商品类型编号
        Arrays.fill(buf, (byte) 0);
        in.read(buf);
        String[] infos = (new String(buf, "utf-8")).trim().split(";");

        // 无意义的信息
        out.write("get an item".getBytes("utf-8"));

        // 服务器发送该商品的图片的字节大小
        Arrays.fill(buf, (byte) 0);
        in.read(buf);
        int size = Integer.parseInt((new String(buf, "utf-8")).trim());
        byte[] bytes = new byte[size];

        for (int i = 0; i < size; i += 1024) {
            out.write("getting image".getBytes("utf-8"));
            in.read(bytes, i, Math.min(1024, size - i));
        }

        return new Item(infos[0], Integer.parseInt(infos[1]),
                Integer.parseInt(infos[2]), Integer.parseInt(infos[3]),
                PicProcess.bytes2bitmap(bytes));
    }

    /**
     * 向服务器请求, 获取该用户的所有商品
     *
     * @param itemList 保存结果的列表
     */
    public static void getSellerItems(final List<Item> itemList) {
        Runnable gsi = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    // 0. 发送控制信息: get seller item
                    out.write("get seller item".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 1. 发送 userid, 然后服务器返回该卖家有多少商品
                    out.write(LoginStatus.userid.getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    int cnt = Integer.parseInt((new String(buf, "utf-8")).trim());

                    // 接收这 cnt 个商品
                    for (int i = 0; i < cnt; i++) {
                        itemList.add(getAnItem(in, out, buf));
                    }

                } catch (Exception e) {
                }
            }
        };

        Thread thread = new Thread(gsi);
        thread.start();
    }

    public static void getRecommendedItems(final List<Item> itemList) {
        Runnable gri = new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(serverIP, serverPort);
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    byte[] buf = new byte[1024];

                    // 发送控制信息: get recommends
                    out.write("get recommends".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);

                    // 服务器发送推荐的商品的个数
                    out.write("null msg".getBytes("utf-8"));
                    Arrays.fill(buf, (byte) 0);
                    in.read(buf);
                    int cnt = Integer.parseInt((new String(buf, "utf-8")).trim());

                    // 接收这 cnt 个商品
                    for (int i = 0; i < cnt; i++) {
                        itemList.add(getAnItem(in, out, buf));
                    }

                } catch (Exception e) {
                }
            }
        };

        Thread thread = new Thread(gri);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {

        }
    }
}

class PicProcess {
    public static byte[] bitmap2bytes(Bitmap bm) {
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 0, s);
        return s.toByteArray();
    }

    public static Bitmap bytes2bitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}