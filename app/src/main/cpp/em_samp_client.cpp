#include <jni.h>
#include <android/log.h>

#define LOG_TAG "EM-SAMP"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jstring JNICALL
Java_com_emrp_client_MainActivity_nativeGetClientStatus(
        JNIEnv* env,
        jobject /* thiz */) {

    LOGI("EM-SAMP native client loaded");

    return env->NewStringUTF(
        "EM-SAMP native client initialized"
    );
}
