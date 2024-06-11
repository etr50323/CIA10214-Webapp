package com.cust.model;

import java.util.*;
import java.sql.*;

public class CustJDBCDAO implements CustDAO_interface {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Taipei";
        String userid = "root";
        String passwd = "azx4639551";

        private static final String INSERT_STMT = 
                "INSERT INTO customization_Options (cust_name) VALUES (?)";
        private static final String GET_ALL_STMT = 
                "SELECT cust_id, cust_name FROM customization_Options order by cust_id";
        private static final String GET_ONE_STMT = 
                "SELECT cust_id,cust_name FROM customization_Options where cust_id = ?";
        private static final String DELETE = 
                "DELETE FROM customization_Options where cust_id = ?";
        private static final String UPDATE = 
                "UPDATE customization_Options set cust_name=? where cust_id = ?";

    @Override
    public void insert(CustVO custVO) {

            Connection con = null;
            PreparedStatement pstmt = null;

            try {

                    Class.forName(driver);
                    con = DriverManager.getConnection(url, userid, passwd);
                    pstmt = con.prepareStatement(INSERT_STMT);

                    pstmt.setString(1, custVO.getCustName());

                    pstmt.executeUpdate();

                    // Handle any driver errors
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Couldn't load database driver. "
                                    + e.getMessage());
                    // Handle any SQL errors
            } catch (SQLException se) {
                    throw new RuntimeException("A database error occured. "
                                    + se.getMessage());
                    // Clean up JDBC resources
            } finally {
                    if (pstmt != null) {
                            try {
                                    pstmt.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (con != null) {
                            try {
                                    con.close();
                            } catch (Exception e) {
                                    e.printStackTrace(System.err);
                            }
                    }
            }

    }

    @Override
    public void update(CustVO custVO) {

            Connection con = null;
            PreparedStatement pstmt = null;

            try {

                    Class.forName(driver);
                    con = DriverManager.getConnection(url, userid, passwd);
                    pstmt = con.prepareStatement(UPDATE);

                    pstmt.setString(1, custVO.getCustName());
                    pstmt.setInt(2, custVO.getCustId());

                    pstmt.executeUpdate();

                    // Handle any driver errors
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Couldn't load database driver. "
                                    + e.getMessage());
                    // Handle any SQL errors
            } catch (SQLException se) {
                    throw new RuntimeException("A database error occured. "
                                    + se.getMessage());
                    // Clean up JDBC resources
            } finally {
                    if (pstmt != null) {
                            try {
                                    pstmt.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (con != null) {
                            try {
                                    con.close();
                            } catch (Exception e) {
                                    e.printStackTrace(System.err);
                            }
                    }
            }

    }

    @Override
    public void delete(Integer custId) {

            Connection con = null;
            PreparedStatement pstmt = null;

            try {

                    Class.forName(driver);
                    con = DriverManager.getConnection(url, userid, passwd);
                    pstmt = con.prepareStatement(DELETE);

                    pstmt.setInt(1, custId);

                    pstmt.executeUpdate();

                    // Handle any driver errors
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Couldn't load database driver. "
                                    + e.getMessage());
                    // Handle any SQL errors
            } catch (SQLException se) {
                    throw new RuntimeException("A database error occured. "
                                    + se.getMessage());
                    // Clean up JDBC resources
            } finally {
                    if (pstmt != null) {
                            try {
                                    pstmt.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (con != null) {
                            try {
                                    con.close();
                            } catch (Exception e) {
                                    e.printStackTrace(System.err);
                            }
                    }
            }

    }

    @Override
    public CustVO findByPrimaryKey(Integer custId) {

            CustVO custVO = null;
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {

                    Class.forName(driver);
                    con = DriverManager.getConnection(url, userid, passwd);
                    pstmt = con.prepareStatement(GET_ONE_STMT);

                    pstmt.setInt(1, custId);

                    rs = pstmt.executeQuery();

                    while (rs.next()) {
                            // custVo 也稱為 Domain objects
                            custVO = new CustVO();
                            custVO.setCustId(rs.getInt("custId"));
                            custVO.setCustName(rs.getString("custName"));
                     
                    }

                    // Handle any driver errors
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Couldn't load database driver. "
                                    + e.getMessage());
                    // Handle any SQL errors
            } catch (SQLException se) {
                    throw new RuntimeException("A database error occured. "
                                    + se.getMessage());
                    // Clean up JDBC resources
            } finally {
                    if (rs != null) {
                            try {
                                    rs.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (pstmt != null) {
                            try {
                                    pstmt.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (con != null) {
                            try {
                                    con.close();
                            } catch (Exception e) {
                                    e.printStackTrace(System.err);
                            }
                    }
            }
            return custVO;
    }

    @Override
    public List<CustVO> getAll() {
            List<CustVO> list = new ArrayList<CustVO>();
            CustVO custVO = null;

            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {

                    Class.forName(driver);
                    con = DriverManager.getConnection(url, userid, passwd);
                    pstmt = con.prepareStatement(GET_ALL_STMT);
                    rs = pstmt.executeQuery();

                    while (rs.next()) {
                            // custVO 也稱為 Domain objects
                            custVO = new CustVO();
                            custVO.setCustId(rs.getInt("custId"));
                            custVO.setCustName(rs.getString("custName"));
                            
                            list.add(custVO); // Store the row in the list
                    }

                    // Handle any driver errors
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Couldn't load database driver. "
                                    + e.getMessage());
                    // Handle any SQL errors
            } catch (SQLException se) {
                    throw new RuntimeException("A database error occured. "
                                    + se.getMessage());
                    // Clean up JDBC resources
            } finally {
                    if (rs != null) {
                            try {
                                    rs.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (pstmt != null) {
                            try {
                                    pstmt.close();
                            } catch (SQLException se) {
                                    se.printStackTrace(System.err);
                            }
                    }
                    if (con != null) {
                            try {
                                    con.close();
                            } catch (Exception e) {
                                    e.printStackTrace(System.err);
                            }
                    }
            }
            return list;
    }

    public static void main(String[] args) {

            CustJDBCDAO dao = new CustJDBCDAO();

            // 新增
            CustVO custVO1 = new CustVO();
            custVO1.setCustName("冰的");
  
            dao.insert(custVO1);

            // 修改
            CustVO custVO2 = new CustVO();
            custVO2.setCustId(1);
            custVO2.setCustName("熱的");
   
            dao.update(custVO2);

            // 刪除
            dao.delete(6);

            // 查詢
            CustVO custVO3 = dao.findByPrimaryKey(1);
            System.out.print(custVO3.getCustId() + ",");
            System.out.print(custVO3.getCustName() + ",");          
            System.out.println("---------------------");

           // 查詢
            List<CustVO> list = dao.getAll();
            for (CustVO aCust : list) {
                    System.out.print(aCust.getCustId() + ",");
                    System.out.print(aCust.getCustName() + ",");                 
                    System.out.println();
            }
    }
}
