package com.royarn.mini;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.*;
import com.royarn.mini.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

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

	@Test
	public void test2() {
		List<Group> groups = new ArrayList<>();
		for (int i=100; i< 106;i++) {
			Group group = new Group();
			group.setId(UUID.randomUUID().toString());
			group.setParentId("00000000-0000-0000-0000-000000000000");
			group.setName("市局00" + String.valueOf(i));
			group.setAreaCode("2"+String.valueOf(i));
			group.setMappingCode("100" + String.valueOf(i));
			group.setSort(i);
			group.setDescription("test_group_" + String.valueOf(i));
			group.setCode("code_" + String.valueOf(i));
			group.setRelationId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
			groups.add(group);
		}
		config.mongoTemplate().insert(groups, Group.class);
	}


	@Test
	public void test3() {
		//3328a7d3-6d5d-442d-b932-fd097f10318e   52003aea-5c05-4003-9e24-ad15eeb08e58  3e838769-4656-403b-b5b9-7dee7c55dabb
		//8e92baf5-7937-434c-86c9-826d8687a943   4223981c-65ba-4d8a-ae1d-d6db67533f00    1e16067d-3f90-4ddb-ab9d-94e10df73c4a
		List<String> ids = new ArrayList<>();
		ids.add("7bc1ca2c-acc6-4561-839c-8df1e21953cf");
		ids.add("3606aff8-67fa-4c86-8ee0-0f03a16c90b0");
		ids.add("e94d69c7-31c3-47ac-b28f-9b9c446821bc");
		ids.add("f4bfa705-1676-4917-8e59-06377e547ff9");
		ids.add("98836f87-eba1-4c52-926d-9eaf1148f121");
		List<Group> groups = new ArrayList<>();
		List<Device> devices = new ArrayList<>();
		List<TempTable> tempTables = new ArrayList<>();
		ids.stream()
				.forEach(id -> {
					for (int i=1; i< 21;i++) {
						//分组
						Group other = new Group();
						other.setId(UUID.randomUUID().toString());
						other.setParentId(id);
						other.setName("分局01" + String.valueOf(i));
						other.setAreaCode("1"+String.valueOf(i));
						other.setMappingCode("100" + String.valueOf(i));
						other.setSort(i);
						other.setDescription("test_group_" + String.valueOf(i));
						other.setCode("code_" + String.valueOf(i));
						other.setRelationId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
						groups.add(other);
						//设备
						for (int d=1;d<101;d++) {
							Device device = new Device();
							device.setId(UUID.randomUUID().toString());
							device.setName("112_18.23.32_" + String.valueOf(d));
							device.setPinYin("112_18.23.32_" + String.valueOf(d));
							device.setPinYinAd("112_18.23.32_" + String.valueOf(d));
							device.setAccess("sdk");
							device.setGroupId(other.getId());
							device.setUseType("camera");
							device.setPauServiceId("");
							device.setServiceId("");
							device.setDeviceType("");
							device.setChannelNum(12);
							device.setDeviceType("camera");
							device.setChannelNum(1);
							device.setStatus(0);
							device.setSort(Long.valueOf(d));
							device.setVoiceTalk(1);
							device.setChannelNum(1);
							device.setHost("");
							device.setPort(8080);
							device.setForwardFlag(false);
							devices.add(device);
						}
						for (int j = 1; j < 21;j++) {
							Group group = new Group();
							group.setId(UUID.randomUUID().toString());
							group.setParentId(other.getId());
							group.setName("派出所02" + String.valueOf(j));
							group.setAreaCode("2"+String.valueOf(j));
							group.setMappingCode("200" + String.valueOf(j));
							group.setSort(j);
							group.setDescription("test_group_" + String.valueOf(j));
							group.setCode("code_" + String.valueOf(j));
							group.setRelationId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
							groups.add(group);
						}
					}
				});
		config.mongoTemplate().insert(groups, Group.class);
		config.mongoTemplate().insert(devices, Device.class);
		//config.mongoTemplate().insert(tempTables, TempTable.class);
	}

	@Test
	public void test4() {
		List<Group> groupList = config.mongoTemplate().find(new Query(), Group.class);
		for (int l =0;l<groupList.size();l++) {
			Group group = groupList.get(l);
			List<Camera> cameras = new ArrayList<>();
			for (int i=0;i<5000;i++) {
				Camera camera = new Camera();
				int num = new Random().nextInt(10000000) + 1;
				StringBuffer buffer = new StringBuffer();
				buffer.append(String.valueOf(new Random().nextInt(200) + 1));
				buffer.append(".");
				buffer.append(String.valueOf(new Random().nextInt(200) + 1));
				buffer.append(".");
				buffer.append(String.valueOf(new Random().nextInt(200) + 1));
				buffer.append(".");
				buffer.append(String.valueOf(new Random().nextInt(200) + 1));
				String ip = buffer.toString();
				camera.setId(UUID.randomUUID().toString());
				camera.setCameraNo(String.valueOf(num));
				camera.setName(ip);
				camera.setPinYin(ip);
				camera.setPinYinAd(ip);
				camera.setLevel(1);
				camera.setCid(0l);
				camera.setNodeId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
				camera.setGroupId(group.getId());
				camera.setDeviceId("");
				camera.setDeviceType(0);
				camera.setChanNo("/" + String.valueOf(num));
				camera.setChanParam(String.valueOf(num));
				camera.setGbId(String.valueOf(num));
				camera.setGbIdMap("");
				camera.setDpi("");
				camera.setPtz("");
				camera.setLongitude(0.0);
				camera.setLatitude(0.0);
				camera.setMerge("");
				camera.setExt("");
				camera.setType("local");
				camera.setStatus(1);
				camera.setRelationId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
				camera.setFromGroupId("e553cca4-f7a3-4923-87bb-4b8aff23a598");
				camera.setFromMerge("");
				camera.setTransport("");
				camera.setSort(Long.valueOf(num));
				camera.setPath("");
				camera.setManufacturer("");
				camera.setModel("");
				camera.setOwner("");
				camera.setBlock("");
				camera.setAddress("");
				camera.setParental(1);
				camera.setSafetyway(0);
				camera.setSecrecy(0);
				camera.setPosition(0);
				camera.setRoom(0);
				camera.setUses(0);
				camera.setSupplylight(0);
				camera.setDirection(0);
				camera.setStreamNumber(0);
				camera.setAnalogType(0);
				camera.setMac("mac");
				camera.setVersion(0L);
				camera.setChanSort("");
				cameras.add(camera);
			}
			config.mongoTemplate().insert(cameras, Camera.class);
		}
	}

	@Test
	public void test6() {
		String id = "3973fea9-25a5-465b-ad67-69aadebe03cc";
		id = id.substring(0, id.length()-8);
		System.out.println(id);
	}
}