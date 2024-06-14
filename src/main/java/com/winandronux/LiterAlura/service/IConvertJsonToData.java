package com.winandronux.LiterAlura.service;

public interface IConvertJsonToData  {
    <T> T getData(String json, Class<T> c);
}
