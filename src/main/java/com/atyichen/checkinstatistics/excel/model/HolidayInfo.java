package com.atyichen.checkinstatistics.excel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 节假日信息表
 * @TableName holiday_info
 */
@TableName(value ="holiday_info")
@Data
public class HolidayInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 节假日日期
     */
    private String date;

    /**
     * 节假日名称
     */
    private String name;

    /**
     * 薪资倍数
     */
    private Integer wage;

    /**
     * 节假日类型：0工作日、1周末、2节日、3调休
     */
    private Integer type;

    /**
     * 周几：1-7表示周一到周日
     */
    private Integer week;

    /**
     * 
     */
    @TableField("create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}