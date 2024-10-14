//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class DeviceInfoDao {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public int addDeviceInfo(DeviceInfo deviceInfo) {
//        return jdbcTemplate.update("INSERT INTO device_info VALUES (?, ?, ?)", deviceInfo.deviceName, deviceInfo.deviceId, deviceInfo.type);
//    }
//
//    public DeviceInfo getDeviceInfo(String deviceId) {
//        String sql = "SELECT * FROM device_info WHERE device_id = ?";
//        return jdbcTemplate.queryForObject(sql, new DeviceInfoRowMapper(), deviceId);
//    }
//
//    public List<DeviceInfo> getAllDeviceInfo() {
//        return jdbcTemplate.query("SELECT * FROM device_info", new DeviceInfoRowMapper());
//    }
//
//    static class DeviceInfoRowMapper implements RowMapper<DeviceInfo> {
//        @Override
//        public DeviceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new DeviceInfo(
//                    rs.getString("device_id"),
//                    rs.getString("device_name"),
//                    rs.getString("device_type"));
//        }
//    }
//}
