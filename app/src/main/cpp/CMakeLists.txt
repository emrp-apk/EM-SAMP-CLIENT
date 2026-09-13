cmake_minimum_required(VERSION 3.22.1)

project("em_samp_client")

add_library(
    em_samp_client
    SHARED
    em_samp_client.cpp
)

find_library(
    log-lib
    log
)

target_link_libraries(
    em_samp_client
    ${log-lib}
)
