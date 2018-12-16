<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage}.${dao}" >
    <resultMap id="BaseResultMap" type="${entityPackage}.${className}" >
        <#if list??>
            <#list list as key>
                <#if key.ifKey==true >
        <id column="${key.column_name}" property="${key.javaName}" jdbcType="${key.mybatisColumnType}" />
                <#else>
        <result column="${key.column_name}" property="${key.javaName}" jdbcType="${key.mybatisColumnType}" />
                </#if>
            </#list>
        </#if>
    </resultMap>
    <sql id="Base_Column_List" >
       ${COLUMN_LIST}
    </sql>
    <sql id="table">
        ${table}
    </sql>
    <sql id="condition_item">
         <#if list??>
             <#list list as key>
         <if test="item.${key.javaName} != null" >
            ${key.column_name}=#'{item.${key.javaName},jdbcType=${key.mybatisColumnType}'}
         </if>
             </#list>
         </#if>
    </sql>
    <sql id="condition">
         <#if list??>
             <#list list as key>
         <if test="${key.javaName} != null" >
            ${key.column_name}=#'{${key.javaName},jdbcType=${key.mybatisColumnType}'}
         </if>
             </#list>
         </#if>
    </sql>

    <sql id="keyId">
        ${db_id}=#'{${java_id}}'
    </sql>
    <sql id="keyId_item">
        ${db_id}=#'{item.${java_id}'}
    </sql>
    <sql id="putValue">
        <#if list??>
            <#list list as key>
        <if test="${key.javaName} != null" >
            #'{${key.javaName},jdbcType=${key.mybatisColumnType}'},
        </if>
            </#list>
        </#if>
    </sql>
    <sql id="conditionKey">
         <#if list??>
             <#list list as key>
         <if test="${key.javaName} != null" >
            ${key.column_name},
         </if>
             </#list>
         </#if>
    </sql>
    <sql id="condition_value_add">
         <#if list??>
             <#list list as key>
                 <#if key.ifKey==true >
        <if test="${key.javaName} == null" >
            ((SELECT REPLACE(UUID(), '-', '') AS ${key.column_name}),
        </if>
        <if test="${key.javaName} != null" >
            #'{${key.javaName}'},
        </if>
                 <#else>
        <if test="${key.javaName} != null" >
            #'{${key.javaName}'},
        </if>
                 </#if>
             </#list>
         </#if>
    </sql>
    <sql id="condition_value_add_item">
         <#if list??>
             <#list list as key>
                 <#if key.ifKey==true >
        <if test="item.${key.javaName} == null" >
            ((SELECT REPLACE(UUID(), '-', '') AS ${key.column_name}),
        </if>
        <if test="item.${key.javaName} != null" >
            #'{item.${key.javaName}'},
        </if>
                 <#else>
        <if test="item.${key.javaName} != null" >
            #'{item.${key.javaName}'},
        </if>
                 </#if>
             </#list>
         </#if>
    </sql>
    <sql id="condition_value_update">
        <#if list??>
            <#list list as key>
         <if test="${key.javaName} != null" >
             ${key.column_name}=#'{${key.javaName},jdbcType=${key.mybatisColumnType}'},
         </if>
            </#list>
        </#if>
    </sql>
    <sql id="condition_value_update_item">
         <#if list??>
             <#list list as key>
         <if test="item.${key.javaName} != null" >
            ${key.column_name}=#'{item.${key.javaName},jdbcType=${key.mybatisColumnType}'},
         </if>
             </#list>
         </#if>
    </sql>
    <sql id="order">
        ORDER BY
    '${'order'}' '${'sort'}'
    </sql>
    <sql id="group">
        GROUP BY '${'group'}'
    </sql>

    <select id="findPageBy" resultMap="BaseResultMap" parameterType="${aliases_table}">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="table"/>
        <where>
            <include refid="condition"/>
        </where>
        <if test="group != null" >
            <include refid="group"/>
        </if>
        <if test="order != null" >
            <include refid="order"/>
        </if>
    </select>
    <select id="findById"  resultMap="BaseResultMap" parameterType="string" >
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="table"/>
        <where>
            <include refid="keyId"/>
        </where>
    </select>

    <select id="findListBy"  resultMap="BaseResultMap" parameterType="${aliases_table}" >
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="table"/>
        <where>
            <include refid="condition"/>
        </where>
    </select>

    <select id="findByList"  resultMap="BaseResultMap" parameterType="list" >
        <foreach  item="item" collection="list" separator=";" open="(" close=")">
            SELECT
            <include refid="Base_Column_List"/>
            FROM
            <include refid="table"/>
            <where>
                <include refid="condition_item"/>
            </where>
        </foreach>
    </select>
    <insert id="save" parameterType="${aliases_table}" >
        insert into <include refid="table"/>
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <include refid="conditionKey"/>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
            <include refid="condition_value_add"/>
        </trim>
    </insert>
    <insert id="saveList" parameterType="list" >
        insert into <include refid="table"/>
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <include refid="conditionKey"/>
        </trim>
        <foreach collection="list" index="index" item="item" separator=",">
            ( <include refid="condition_value_add_item"/>)
        </foreach>
    </insert>
    <update id="update" parameterType="${aliases_table}">
        update <include refid="table"/>
        <set >
            <include refid="condition_value_update"/>
        </set>
        <where>
            <include refid="keyId"/>
        </where>
    </update>
    <update id="updateList" parameterType="list">
        <foreach  item="item" collection="list" separator=";" open="(" close=")">
            update <include refid="table"/>
            <set >
                <include refid="condition_value_update_item"/>
            </set>
            <where>
                <include refid="keyId_item"/>
            </where>
        </foreach>
    </update>
    <delete id="deleteById" parameterType="string" >
        delete from
        <include refid="table"/>
        <where>
            <include refid="keyId"/>
        </where>
    </delete>
</mapper>


