package project.dao.impl;

import org.springframework.stereotype.Component;
import project.dao.IGenericDao;
import project.dto.FormLoginDto;
import project.dto.FormRegisterDto;
import project.model.User;
import project.util.ConnectDB;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao implements IGenericDao<User, Long> {
    private final String FINDALL = "SELECT * FROM USERS";
    private final String FINDBYID = "SELECT * FROM USERS WHERE ID = ?";
     private final String INSERT = "INSERT INTO  USERS(username,email,password,status,role_id) values(?,?,?,?,?)";
    private final String UPDATE = "UPDATE USERS full_name=?, avatar=? where id = ?";
    private final String DELETE = "DELETE  FROM USERS WHERE ID =?";
    private final String LOGIN = "SELECT * FROM USERS WHERE username = ? and password = ?";
    private final String REGISTER = "SELECT * FROM USERS WHERE username = ? and email=?   and password = ?  ";

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectDB.getConnection();
            // Chuẩn bị câu lệnh gọi thủ tục lưu trữ để tìm tất cả người dùng
            CallableStatement callSt = conn.prepareCall(FINDALL);
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                // TODO: Đọc dữ liệu từ ResultSet và tạo đối tượng User, sau đó thêm vào danh sách 'list'
            }

        } catch (SQLException e) {
            // Xử lý ngoại lệ bằng cách ném một ngoại lệ RuntimeException với thông tin từ SQLException
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng kết nối đến cơ sở dữ liệu được đóng sau khi hoàn thành
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(User user) {
        Connection conn = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectDB.getConnection();

            // Chuẩn bị câu lệnh gọi thủ tục lưu trữ để thêm mới hoặc cập nhật người dùng
            CallableStatement callSt = null;
            if (user.getId() == null) {
                // Chức năng thêm mới
                callSt = conn.prepareCall(INSERT);
                // Truyền tham số vào câu lệnh
                callSt.setString(1, user.getUsername());
                callSt.setString(2, user.getEmail());
                callSt.setString(3, user.getPassword());
                callSt.setBoolean(4, user.isStatus());
                callSt.setLong(5, user.getRoleId());
                // Thực thi câu lệnh SQL để thêm mới
                callSt.executeUpdate();
            } else {
                // TODO: Xử lý chức năng cập nhật
            }

        } catch (SQLException e) {
            // Xử lý ngoại lệ bằng cách ném một ngoại lệ RuntimeException với thông tin từ SQLException
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng kết nối đến cơ sở dữ liệu được đóng sau khi hoàn thành
            ConnectDB.closeConnection(conn);
        }
    }


    @Override
    public User findById(Long id) {
        User user = null;
        Connection conn = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectDB.getConnection();
            // Chuẩn bị câu lệnh gọi thủ tục lưu trữ để tìm người dùng theo ID
            CallableStatement callSt = conn.prepareCall(FINDBYID);
            // TODO: Truyền tham số cho câu lệnh callSt
            // Thực thi câu lệnh SQL để tìm kiếm
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                // TODO: Đọc dữ liệu từ ResultSet và tạo đối tượng User
            }

        } catch (SQLException e) {
            // Xử lý ngoại lệ bằng cách ném một ngoại lệ RuntimeException với thông tin từ SQLException
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng kết nối đến cơ sở dữ liệu được đóng sau khi hoàn thành
            ConnectDB.closeConnection(conn);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectDB.getConnection();

            // Chuẩn bị câu lệnh gọi thủ tục lưu trữ để xóa người dùng theo ID
            CallableStatement callSt = conn.prepareCall(DELETE);
            // TODO: Truyền tham số cho câu lệnh callSt

            // Thực thi câu lệnh SQL để xóa
            callSt.executeUpdate();

        } catch (SQLException e) {
            // Xử lý ngoại lệ bằng cách ném một ngoại lệ RuntimeException với thông tin từ SQLException
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng kết nối đến cơ sở dữ liệu được đóng sau khi hoàn thành
            ConnectDB.closeConnection(conn);
        }
    }


    public User login(FormLoginDto formLoginDto) {
        User user = null; // Khởi tạo biến user với giá trị ban đầu là null
        Connection conn = null; // Khởi tạo biến conn để lưu trữ kết nối đến cơ sở dữ liệu

        try {
            conn = ConnectDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu thông qua ConnectDB

            // Chuẩn bị một CallableStatement để thực hiện cuộc gọi thủ tục "LOGIN" trong cơ sở dữ liệu
            CallableStatement callSt = conn.prepareCall(LOGIN);
            callSt.setString(1, formLoginDto.getUsername()); // Đặt giá trị tham số đầu tiên là tên đăng nhập từ FormLoginDto
            callSt.setString(2, formLoginDto.getPassword()); // Đặt giá trị tham số thứ hai là mật khẩu từ FormLoginDto

            ResultSet rs = callSt.executeQuery(); // Thực thi câu truy vấn và lấy kết quả trả về (dạng bảng kết quả) vào ResultSet

            // Duyệt qua các dòng kết quả trong ResultSet
            while (rs.next()) {
                user = new User(); // Khởi tạo một đối tượng User để lưu trữ thông tin người dùng từ dòng kết quả hiện tại
                user.setId(rs.getLong("id")); // Lấy giá trị cột "id" từ kết quả và đặt vào thuộc tính id của đối tượng User
                user.setUsername(rs.getString("username")); // Lấy giá trị cột "username" từ kết quả và đặt vào thuộc tính username của đối tượng User
                user.setPassword(rs.getString("password")); // Lấy giá trị cột "password" từ kết quả và đặt vào thuộc tính password của đối tượng User
                user.setStatus(rs.getBoolean("status")); // Lấy giá trị cột "password" từ kết quả và đặt vào thuộc tính password của đối tượng User
                user.setRoleId(rs.getLong("role_id")); // Lấy giá trị cột "role_id" từ kết quả và đặt vào thuộc tính roleId của đối tượng User
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra và ném một ngoại lệ mới có thông tin lỗi
        } finally {
            ConnectDB.closeConnection(conn); // Đảm bảo luôn đóng kết nối sau khi thực hiện xong
        }

        return user; // Trả về đối tượng User chứa thông tin người dùng (hoặc null nếu không tìm thấy người dùng)
    }

    public User register(FormRegisterDto formRegisterDto) {
        User user = null; // Khởi tạo biến user với giá trị ban đầu là null
        Connection conn = null; // Khởi tạo biến conn để lưu trữ kết nối đến cơ sở dữ liệu
        try {
            conn = ConnectDB.getConnection(); // Lấy kết nối đến cơ sở dữ liệu thông qua ConnectDB
            // Chuẩn bị một CallableStatement để thực hiện cuộc gọi thủ tục "LOGIN" trong cơ sở dữ liệu
            CallableStatement callSt = conn.prepareCall(REGISTER);
            callSt.setString(1, formRegisterDto.getUsername()); // Đặt giá trị tham số đầu tiên là tên đăng nhập từ formRegisterDto
            callSt.setString(2, formRegisterDto.getEmail()); // Đặt giá trị tham số thứ hai là email từ formRegisterDto
            callSt.setString(3, formRegisterDto.getPassword()); // Đặt giá trị tham số thứ ba là mật khẩu từ formRegisterDto
            ResultSet rs = callSt.executeQuery(); // Thực thi câu truy vấn và lấy kết quả trả về (dạng bảng kết quả) vào ResultSet

            // Duyệt qua các dòng kết quả trong ResultSet
            while (rs.next()) {
                user = new User(); // Khởi tạo một đối tượng User để lưu trữ thông tin người dùng từ dòng kết quả hiện tại
                user.setId(rs.getLong("id")); // Lấy giá trị cột "id" từ kết quả và đặt vào thuộc tính id của đối tượng User
                user.setUsername(rs.getString("username")); // Lấy giá trị cột "username" từ kết quả và đặt vào thuộc tính username của đối tượng User
                user.setEmail(rs.getString("email")); // Lấy giá trị cột "password" từ kết quả và đặt vào thuộc tính password của đối tượng User
                user.setPassword(rs.getString("password")); // Lấy giá trị cột "password" từ kết quả và đặt vào thuộc tính password của đối tượng User
             }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Xử lý ngoại lệ nếu có lỗi xảy ra và ném một ngoại lệ mới có thông tin lỗi
        } finally {
            ConnectDB.closeConnection(conn); // Đảm bảo luôn đóng kết nối sau khi thực hiện xong
        }

        return user; // Trả về đối tượng User chứa thông tin người dùng (hoặc null nếu không tìm thấy người dùng)
    }
}
