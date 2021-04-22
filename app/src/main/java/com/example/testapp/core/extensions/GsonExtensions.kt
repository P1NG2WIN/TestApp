package com.example.testapp.core.extensions

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.io.Reader

inline fun <reified T> Gson.toJsonWithType(list: T): String? {
    return try {
        toJson(list, object : TypeToken<T>() {}.type)
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> Gson.toJsonTreeWithType(list: T): JsonElement? {
    return try {
        toJsonTree(list, object : TypeToken<T>() {}.type)
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> Gson.fromJsonWithType(json: JsonObject?): T? {
    if (json == null) return null
    return try {
        fromJson<T>(json, object : TypeToken<T>() {}.type)
    } catch (e: JsonSyntaxException) {
        Timber.e(e)
        null
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> Gson.fromJsonWithType(json: JsonArray?): T? {
    if (json == null) return null
    return try {
        fromJson<T>(json, object : TypeToken<T>() {}.type)
    } catch (e: JsonSyntaxException) {
        Timber.e(e)
        null
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> Gson.fromJsonWithType(json: Reader?): T? {
    if (json == null) return null
    return try {
        fromJson<T>(json, object : TypeToken<T>() {}.type)
    } catch (e: JsonSyntaxException) {
        Timber.e(e)
        null
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> Gson.fromJsonWithType(json: String?): T? {
    if (json == null) return null
    return try {
        fromJson<T>(json, object : TypeToken<T>() {}.type)
    } catch (e: JsonSyntaxException) {
        Timber.e(e)
        null
    } catch (e: JsonIOException) {
        Timber.e(e)
        null
    }
}

inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}
