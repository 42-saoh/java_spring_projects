package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConfig {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/goods";
        String usr = "saoh";
        String pwd = "password";
        String goods = "create table goods(id int generated always as identity, goods_name varchar(20) not null, price int not null, stock int not null, primary key(id));";
        String orders = "create table orders(id int generated always as identity, goods_id int not null, volume int not null, primary key(id), CONSTRAINT fk_goods_id FOREIGN KEY(goods_id) REFERENCES goods(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        String insert_goods = "insert into goods(goods_name, price, stock) values ('soda', 100, 20);" +
                "insert into goods(goods_name, price, stock) values ('coke', 200, 50);";
        String insert_orders = "insert into orders(goods_id, volume) values (1, 20);" +
                "insert into orders(goods_id, volume) values (2, 100);";
        String select_orders = "select * from orders;";
        String select_goods = "select * from goods;";
        String select_join = "select ord.id, ord.volume, ord.goods_id, goo.goods_name, goo.price, goo.stock from orders as ord join goods as goo on ord.goods_id = goo.id;";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(goods);
            stmt.executeUpdate(orders);
            stmt.executeUpdate(insert_goods);
            stmt.executeUpdate(insert_orders);
            ResultSet rs = stmt.executeQuery(select_goods);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("goods_name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                System.out.println(id + "\t" + name + "\t" + price + "\t" + stock);
            }
            rs = stmt.executeQuery(select_orders);
            while (rs.next()) {
                String id = rs.getString("id");
                String goodsId = rs.getString("goods_id");
                String volume = rs.getString("volume");
                System.out.println(id + "\t" + goodsId + "\t" + volume);
            }
            rs = stmt.executeQuery(select_join);
            while (rs.next()) {
                String id = rs.getString("id");
                String goodsId = rs.getString("goods_id");
                String volume = rs.getString("volume");
                String name = rs.getString("goods_name");
                String price = rs.getString("price");
                String stock = rs.getString("stock");
                System.out.println(id + "\t" + goodsId + "\t" + volume + "\t" + name + "\t" + price + "\t" + stock);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
