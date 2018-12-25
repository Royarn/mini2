package com.royarn.mini;

import com.alibaba.fastjson.JSON;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Camera;
import com.royarn.mini.entity.Device;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.TUser;
import com.royarn.mini.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Resource
	TUserService userService;

	@Resource
	private MongoConfig config;


	@Test
	public void test() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://172.16.34.208:3306/np_config?characterEncoding=utf8&useSSL=false",
					"root", "123456");
			Statement st = con.createStatement();
			int pageSize = 10000;
			for (int i =1;i<5000100;i++) {
				ResultSet rs = st.executeQuery("select * from other_groups limit "+ i +", "+ pageSize +"");
				while (rs.next()) {
					Group group = new Group();
//					Device device = new Device();
//					Camera camera = new Camera();
//					camera.setId(rs.getString("id"));
//					camera.setCameraNo(rs.getString("camera_no"));
//					camera.setName(rs.getString("name"));
//					camera.setPinYin(rs.getString("pin_yin"));
//					camera.setPinYinAd(rs.getString("pin_yin_ad"));
//					camera.setLevel(rs.getInt("level"));
//					camera.setCid(rs.getLong("cid"));
//					camera.setNodeId(rs.getString("node_id"));
//					camera.setGroupId(rs.getString("group_id"));
//					camera.setDeviceId(rs.getString("device_id"));
//					camera.setDeviceType(rs.getInt("device_type"));
//					camera.setChanNo(rs.getString("chan_no"));
//					camera.setChanParam(rs.getString("chan_param"));
//					camera.setGbId(rs.getString("gb_id"));
//					camera.setGbIdMap(rs.getString("gb_id_map"));
//					camera.setDpi(rs.getString("dpi"));
//					camera.setPtz(rs.getString("ptz"));
//					camera.setLongitude(rs.getDouble("longitude"));
//					camera.setLatitude(rs.getDouble("latitude"));
//					camera.setMerge(rs.getString("merge"));
//					camera.setExt(rs.getString("ext"));
//					camera.setType(rs.getString("type"));
//					camera.setStatus(rs.getInt("status"));
//					camera.setRelationId(rs.getString("relation_id"));
//					camera.setFromGroupId(rs.getString("from_group_id"));
//					camera.setFromMerge(rs.getString("from_merge"));
//					camera.setTransport(rs.getString("transport"));
//					camera.setSort(rs.getLong("sort"));
//					camera.setPath(rs.getString("path"));
//					camera.setManufacturer(rs.getString("manufacturer"));
//					camera.setModel(rs.getString("model"));
//					camera.setOwner(rs.getString("owner"));
//					camera.setBlock(rs.getString("block"));
//					camera.setAddress(rs.getString("address"));
//					camera.setParental(rs.getInt("parental"));
//					camera.setSafetyway(rs.getInt("safetyway"));
//					camera.setSecrecy(rs.getInt("secrecy"));
//					camera.setPosition(rs.getInt("position"));
//					camera.setRoom(rs.getInt("room"));
//					camera.setUses(rs.getInt("uses"));
//					camera.setSupplylight(rs.getInt("supplylight"));
//					camera.setDirection(rs.getInt("direction"));
//					camera.setStreamNumber(rs.getInt("stream_number"));
//					camera.setAnalogType(rs.getInt("analog_type"));
//					camera.setMac(rs.getString("mac"));
//					camera.setVersion(rs.getLong("version"));
//					camera.setIsLocal(rs.getByte("is_local"));
//					camera.setChanSort(rs.getString("chan_sort"));
//					camera.setRecordKey(rs.getString("record_key"));
//					camera.setVideoFlowType(rs.getInt("video_flow_type"));
//					camera.setFlowPlatformId(rs.getString("flow_platform_id"));
//					camera.setVoiceTalkChannelId(rs.getString("voice_talk_channel_id"));

//					device.setId(rs.getString("id"));
//					device.setName(rs.getString("name"));
//					device.setPinYin(rs.getString("pin_yin"));
//					device.setPinYinAd(rs.getString("pin_yin_ad"));
//					device.setAccess(rs.getString("access"));
//					device.setGroupId(rs.getString("group_id"));
//					device.setUseType(rs.getString("use_type"));
//					device.setPauServiceId(rs.getString("pau_service_id"));
//					device.setServiceId(rs.getString("service_id"));
//					device.setDeviceType(rs.getString("device_type"));
//					device.setChannelNum(rs.getInt("channel_num"));
//					device.setDeviceType(rs.getString("device_type"));
//					device.setChannelNum(rs.getInt("channel_num"));
//					device.setStatus(rs.getInt("status"));
//					device.setSort(rs.getLong("sort"));
//					device.setVoiceTalk(rs.getInt("voice_talk"));
//					device.setChannelNum(rs.getInt("channel_num"));
//					device.setHost(rs.getString("host"));
//					device.setPort(rs.getInt("port"));
//					device.setLevel(rs.getString("level"));
//					device.setDeviceProperty(rs.getString("device_property"));
//					device.setSynchroClock(rs.getInt("synchro_clock"));
//					device.setHeartDetection(rs.getInt("heart_detection"));
//					device.setHeartTimeoutTimes(rs.getInt("heart_timeout_times"));
//					device.setHeartTimeoutTime(rs.getInt("heart_timeout_time"));
//					device.setClosePasswordAuthentication(rs.getInt("close_password_authentication"));
//					device.setQueryDirectory(rs.getInt("query_directory"));
//					device.setSubscribeDirectory(rs.getInt("subscribe_directory"));
//					device.setHeartDetection(rs.getInt("heart_detection"));
//					device.setExpires(rs.getLong("expires"));
//					device.setForwardFlag(rs.getBoolean("forward_flag"));


					group.setId(rs.getString("id"));
					group.setParentId(rs.getString("parent_id"));
					group.setName(rs.getString("name"));
					group.setAreaCode(rs.getString("area_code"));
					group.setMappingCode(rs.getString("mapping_code"));
					group.setSort(rs.getInt("sort"));
					group.setDescription(rs.getString("description"));
					group.setCode(rs.getString("code"));
					group.setRelationId(rs.getString("relation_id"));
					group.setPlatformId(rs.getString("platform_id"));
					config.mongoTemplate().insert(group);
				}
				i = i + pageSize;
			}
 		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}