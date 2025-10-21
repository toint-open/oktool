//package cn.toint.oktool.spring.boot.content.model;
//
//import cn.dev33.satoken.stp.SaTokenInfo;
//import cn.toint.oktool.model.WriteValue;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
///**
// * 任务追踪记录
// *
// * @author Toint
// * @since 2025/10/21
// */
//public class TraceRecord implements WriteValue {
//    /**
//     * 追踪ID
//     */
//    private String traceId;
//
//    /**
//     * 请求URL
//     */
//    private String url;
//
//    /**
//     * HTTP方法
//     */
//    private String method;
//
//    /**
//     * 客户端IP
//     */
//    private String ip;
//
//    /**
//     * 请求开始时间
//     */
//    private LocalDateTime startTime;
//
//    /**
//     * token信息
//     */
//    private SaTokenInfo tokenInfo;
//
//    public String getTraceId() {
//        return traceId;
//    }
//
//    public void setTraceId(String traceId) {
//        this.traceId = traceId;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public SaTokenInfo getTokenInfo() {
//        return tokenInfo;
//    }
//
//    public void setTokenInfo(SaTokenInfo tokenInfo) {
//        this.tokenInfo = tokenInfo;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (object == null || getClass() != object.getClass()) return false;
//        TraceRecord that = (TraceRecord) object;
//        return Objects.equals(traceId, that.traceId) && Objects.equals(url, that.url) && Objects.equals(method, that.method) && Objects.equals(ip, that.ip) && Objects.equals(startTime, that.startTime) && Objects.equals(tokenInfo, that.tokenInfo);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(traceId, url, method, ip, startTime, tokenInfo);
//    }
//
//    @Override
//    public String toString() {
//        return "TraceRecord{" +
//                "traceId='" + traceId + '\'' +
//                ", url='" + url + '\'' +
//                ", method='" + method + '\'' +
//                ", ip='" + ip + '\'' +
//                ", startTime=" + startTime +
//                ", tokenInfo=" + tokenInfo +
//                '}';
//    }
//}
