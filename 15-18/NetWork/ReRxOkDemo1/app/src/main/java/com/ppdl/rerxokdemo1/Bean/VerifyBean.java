package com.ppdl.rerxokdemo1.Bean;

public class VerifyBean {

    /**
     * 1.access_token   :访问令牌
     * 2.token_type     ：目前为beare
     * 3.expires_in     : 过期时间:当access_token过期时，API会返回token_expired错误,这时需要重新申 请access token
     */
    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
