package db;

import java.sql.*;
import java.util.Optional;

public class UserPstmt {

    static public class Goods {
        public int id;
        public String goodsName;
        public int price;
        public int stock;

        public Goods() {}

        public Goods(String goodsName, int price, int stock) {
            this.goodsName = goodsName;
            this.price = price;
            this.stock = stock;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getId() {
            return this.id;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public int getPrice() {
            return this.price;
        }

        public int getStock() {
            return this.stock;
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/goods";
        String usr = "saoh";
        String pwd = "password";
        String goods = "create table goods(id int generated always as identity, goods_name varchar(20) not null, price int not null, stock int not null, primary key(id));";
        String orders = "create table orders(id int generated always as identity, goods_id int not null, volume int not null, primary key(id), CONSTRAINT fk_goods_id FOREIGN KEY(goods_id) REFERENCES goods(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        Goods goods1 = new Goods("soda", 100, 20);
        Goods goods2 = new Goods("coke", 200, 50);
        String sql = "insert into Goods(goods_name, price, stock) values(?, ?, ?)";
        String select_goods = "select * from goods;";


        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate(goods);
            pstmt.setString(1, goods1.getGoodsName());
            pstmt.setInt(2, goods1.getPrice());
            pstmt.setInt(3, goods1.getStock());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                goods1.setId(rs.getInt(1));
            }
            System.out.println(goods1.getId());
            pstmt.setString(1, goods2.getGoodsName());
            pstmt.setInt(2, goods2.getPrice());
            pstmt.setInt(3, goods2.getStock());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                goods2.setId(rs.getInt(1));
            }
            System.out.println(goods2.getId());
            rs = stmt.executeQuery(select_goods);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("goods_name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                System.out.println(id + "\t" + name + "\t" + price + "\t" + stock);
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
