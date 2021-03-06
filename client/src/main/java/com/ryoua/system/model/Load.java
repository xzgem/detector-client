package com.ryoua.system.model;

import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/18
 **/
@Data
@ToString
public class Load {
    private CpuLoad cpuLoad;
    private MemoryLoad memoryLoad;
    private Long createTimeMills;
}
