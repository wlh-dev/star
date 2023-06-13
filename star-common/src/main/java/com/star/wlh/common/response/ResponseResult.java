package com.star.wlh.common.response;


public class ResponseResult<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public ResponseResult(){}

    // 返回数据
    protected static <T> ResponseResult<T> build(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        if (data != null) {
            responseResult.setData(data);
        }
        return responseResult;
    }

    public static <T> ResponseResult<T> build(T body, Integer code, String message) {
        ResponseResult<T> responseResult = build(body);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }

    public static <T> ResponseResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseResult<T> responseResult = build(body);
        responseResult.setCode(resultCodeEnum.getCode());
        responseResult.setMessage(resultCodeEnum.getMessage());
        return responseResult;
    }

    public static<T> ResponseResult<T> ok(){
        return ResponseResult.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> ResponseResult<T> ok(T data){
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> ResponseResult<T> fail(){
        return ResponseResult.fail(null);
    }

    /**
     * 操作失败
     * @param data 元数据
     * @param <T> 数据类型
     * @return
     */
    public static<T> ResponseResult<T> fail(T data){
        ResponseResult<T> responseResult = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public ResponseResult<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ResponseResult<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
