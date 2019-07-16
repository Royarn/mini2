package com.royarn.mini;


import com.google.gson.JsonArray;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.dao.TDeviceMapper;
import com.royarn.mini.entity.*;
import com.royarn.mini.service.*;
import com.royarn.mini.util.GsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniApplicationTests {

	private Logger logger = LoggerFactory.getLogger(MiniApplicationTests.class);

//	@Test
//	public void contextLoads() {
//	}

	@Resource
	TUserService userService;

	@Resource
	private MongoConfig config;

	@Resource
	private JdbcTemplate template;


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
		ids.add("8c3a2f1a-4f11-4123-b42f-6cdf32fe2d2f");
		ids.add("3937a3a0-c503-4611-9846-a118b0d00e61");
		ids.add("d1c8e7fb-727d-4f82-a0b9-a66b13ac1245");
		ids.add("9011fdcf-e5ee-4fe8-9092-370a799d1113");
		ids.add("f23a61ed-d469-4046-9416-5ac0d1392735");
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

	@Test
	public void test7() {
        System.out.println(userService.findAll());
    }

    @Test
    public void test8() {
		List<DevCamera> cameras = template.query("select * from dev_device_camera", new RowMapper<DevCamera>() {
			@Override
			public DevCamera mapRow(ResultSet resultSet, int i) throws SQLException {
				DevCamera devCamera = new DevCamera();
				devCamera.setName(resultSet.getString("name"));
				return devCamera;
			}
		});
		for (DevCamera devCamera : cameras) {
			System.out.println(devCamera.getName());
		}
	}

	@Test
	public void test9() {
		Connection con = null;
		try {
			//f9a10a88-1308-480e-9814-fdadedfbfa25   978cc1e7-c0aa-47b3-8581-878925e1ab62
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://172.16.109.24:3306/np_config?characterEncoding=utf8&useSSL=false",
					"root", "e18b3f95-af19-4ee1-8f54-0998717ba601");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from other_groups where parent_id = '949afe0a-929c-41a3-b508-b5fcfdf485ef' and name like'%派出所%' ORDER BY sort asc;");
			Statement st2 = con.createStatement();
			List<String> ips = Arrays.asList("172.16.102.21", "172.16.102.22", "172.16.102.23",
					"172.16.102.24", "172.16.102.26", "172.16.102.27", "172.16.105.21", "172.16.105.22",
					"172.16.105.23", "172.16.105.24", "172.16.105.25", "172.16.105.26","172.16.105.27",
					"172.16.105.28", "172.16.106.21", "172.16.106.23", "172.16.106.24", "172.16.106.25",
					"172.16.106.26", "172.16.106.27", "172.16.104.23", "172.16.104.24", "172.16.104.25",
					"172.16.104.26", "172.16.104.27");
			int index = 0;
			int ip_index = 0;
			int real =1;
			ips.stream().forEach(s -> System.out.println(s));
			while (rs.next()) {
				if (!rs.getString("id").equals("949afe0a-929c-41a3-b508-b5fcfdf485ef")) {
					if (index == 4) {
						ip_index = ip_index+1;
						index = 0;
					}
					String id = UUID.randomUUID().toString();
					String sql = "INSERT INTO `np_config`.`dev_device` (\n" +
							"\t`id`,\n" +
							"\t`name`,\n" +
							"\t`pin_yin`,\n" +
							"\t`pin_yin_ad`,\n" +
							"\t`access`,\n" +
							"\t`group_id`,\n" +
							"\t`use_type`,\n" +
							"\t`pau_service_id`,\n" +
							"\t`service_id`,\n" +
							"\t`device_type`,\n" +
							"\t`channel_num`,\n" +
							"\t`status`,\n" +
							"\t`sort`,\n" +
							"\t`voice_talk`,\n" +
							"\t`host`,\n" +
							"\t`port`,\n" +
							"\t`level`,\n" +
							"\t`device_property`,\n" +
							"\t`synchro_clock`,\n" +
							"\t`heart_detection`,\n" +
							"\t`heart_timeout_times`,\n" +
							"\t`heart_timeout_time`,\n" +
							"\t`close_password_authentication`,\n" +
							"\t`query_directory`,\n" +
							"\t`subscribe_directory`,\n" +
							"\t`expires`,\n" +
							"\t`forward_flag`,\n" +
							"\t`modify_time` \n" +
							")\n" +
							"VALUES\n" +
							"\t(\n" +
							"\t\t'"+id+"',\n" +
							"\t\t'"+ips.get(ip_index)+"',\n" +
							"\t\t'"+ips.get(ip_index)+"',\n" +
							"\t\t'"+ips.get(ip_index)+"',\n" +
							"\t\t'sdk',\n" +
							"\t\t'"+rs.getString("id")+"',\n" +
							"\t\t'camera',\n" +
							"\t\tNULL,\n" +
							"\t\tNULL,\n" +
							"\t\t'encoder',\n" +
							"\t\t1,\n" +
							"\t\t1,\n" +
							"\t\t"+real+",\n" +
							"\t\t1,\n" +
							"\t\t'"+ips.get(ip_index)+"',\n" +
							"\t\t"+(554+index)+",\n" +
							"\t\t'0',\n" +
							"\t\t'[\\\"alarm\\\"]',\n" +
							"\t\t1,\n" +
							"\t\t1,\n" +
							"\t\t3,\n" +
							"\t\t60,\n" +
							"\t\t0,\n" +
							"\t\t1,\n" +
							"\t\t1,\n" +
							"\t\t3600,\n" +
							"\t\t1,\n" +
							"\t'2018-12-28 14:37:27' \n" +
							"\t);";
					st2.execute(sql);
					String sqlss = "insert into dev_access_sdk(id,protocol,username,password) values ('"+id+"','rtsphost','admin','admin');";
					st2.execute(sqlss);
					index++;
					real++;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Resource
	private DevDeviceService deviceService;
	@Resource
	private TDeviceMapper mapper;

	@Test
	public void test11() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://172.16.109.25:3306/np_config?characterEncoding=utf8&useSSL=false",
//					"root", "f9a10a88-1308-480e-9814-fdadedfbfa25");
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery("select * from dev_device");
//			Statement st2 = con.createStatement();
//			int x =1;
//			while (rs.next()) {
//				for (int i=0;i<10000;i++) {
//					String id = UUID.randomUUID().toString();
//					String name = rs.getString("name") + "_" + String.valueOf(i);
//					String sql = "insert into dev_device_camera(id,name,pin_yin,pin_yin_ad,node_id,group_id,device_id,chan_no,chan_sort) values(" +
//							"'"+id+"','"+name+"','"+name+"','"+name+"','e83490bd-4ce5-4afc-9644-641c32fc0866','"+rs.getString("group_id")+"'," +
//							" '"+rs.getString("id")+"', '"+x+"','"+x+"')";
//					st2.execute(sql);
//					x++;
//				}
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		Camera camera = new Camera();
//		camera.setChanNo(i + "");
//		camera.setNodeId("e83490bd-4ce5-4afc-9644-641c32fc0866");
//		camera.setType("local");
//		camera.setGroupId();
//		camera.setChanSort(CameraService.getChanSort(camera.getChanNo()));
	}

	@Test
	public void test12() {
		deviceService.insert();
	}

	@Test
	public void test13() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://172.16.109.25:3306/np_config?characterEncoding=utf8&useSSL=false",
                    "root", "f9a10a88-1308-480e-9814-fdadedfbfa25");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from other_groups where parent_id = 'eb075ebc-1082-4254-ac47-dfee4be7ab63' ORDER BY sort asc");
            Statement st2 = con.createStatement();
            List<String> ips = Arrays.asList("172.16.102.21", "172.16.102.22", "172.16.102.23", "172.16.102.24",
                    "172.16.102.26", "172.16.102.27", "172.16.105.21", "172.16.105.22",
                    "172.16.105.23", "172.16.105.24", "172.16.105.25", "172.16.105.26","172.16.105.27", "172.16.105.28",
                    "172.16.106.21", "172.16.106.23", "172.16.106.24", "172.16.106.25", "172.16.106.26", "172.16.106.27");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Autowired
	private RedisService redisService;
    @Test
    public void testRedis() {
		List<String> values = redisService.lrange("books", 0, 5);
		System.out.println(GsonUtils.listoStr(values));
	}
}