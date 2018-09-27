package com.royarn.mini.mock;

import com.alibaba.fastjson.JSON;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MultiNetWork {

    public static void main(String[] args) {

        System.out.println(JSON.toJSON(getLocalIPList()));
    }

    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkIntf;
            Enumeration<InetAddress> ipAddrs;
            InetAddress ipAddr;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkIntf = networkInterfaces.nextElement();
                ipAddrs = networkIntf.getInetAddresses();
                while (ipAddrs.hasMoreElements()) {
                    ipAddr = ipAddrs.nextElement();
                    if (ipAddr != null && !ipAddr.isLoopbackAddress() && ipAddr instanceof Inet4Address) { // IPV4
                        ip = ipAddr.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }
}