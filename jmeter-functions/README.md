# metersphere-jmeter-functions

## 用法

${__Mock(@city)}

${__Mock(@integer)}

## 变量说明

`@bool`	随机生成一个布尔值

## 基本变量
`@natural`	返回一个随机的自然数

`@natural(1,101)`	返回一个随机的1-100的自然数（大于等于 1 的整数）

`@integer`	返回一个随机的整数

`@integer(1,100)`	返回随机的1-100的整数

`@floatNumber( 1, 10, 2, 5 )`	返回一个随机的浮点数，整数1-10，小数部分位数的最小值2，最大值5

`@character(pool)`	从字符串池返回随机的字符

`@character('lower')`	返回一个随机的小写字符

`@character('upper')`	返回一个随机的大写字符

`@character('symbol')`	返回一个随机的特殊符号

`@string(pool,1,10)`	从字符串池返回一个随机字符串，字符数1-10

`@range( 1, 100, 1 )`	返回一个整型数组，参数分别：start：起始值，stop：结束值，step：步长

## 日期变量
`@date('yyyy-MM-dd')`	返回一个随机的日期字符串。例：1983-01-29

`@time('HH:mm:ss')`	返回一个随机的时间字符串。 例：20:47:37

`@dateTime('yyyy-MM-dd HH:mm:ss')`	返回一个随机的日期和时间字符串。例：1977-11-17 03:50:15

`@now('yyyy-MM-dd HH:mm:ss')`	返回当前日期字符串。例：2014-04-29 20:08:38

## 主键
`@uuid`	随机生成一个 UUID。例：eFD616Bd-e149-c98E-a041-5e12ED0C94Fd

`@increment(1)`	随机生成主键，从1起，整数自增的步长

## web变量
`@url('http')`	随机生成一个http URL

`@protocol`	随机生成一个 URL 协议。例：http ftp

`@domain`	随机生成一个域名

`@tld`	随机生成一个顶级域名。例：net

`@email`	随机生成一个邮件地址

`@ip`	随机生成一个IP地址

## 地区
`@region`	随机生成一个（中国）大区。例：华北

`@province`	随机生成一个（中国）省（或直辖市、自治区、特别行政区）

`@city`	随机生成一个（中国）市

`@county	`随机生成一个（中国）县

`@county`	随机生成一个（中国）县（带省市）。例：甘肃省 白银市 会宁县

## 邮编变量

`@zip`	随机生成一个邮政编码

## 身份证号码

`@idCard`	随机生成一个身份证号（生成的身份证号码并不一定是真实有效的）

## 手机号

`@phoneNumber`	随机生成一个手机号（生成的手机号码并不一定是真实有效的）

## 人名变量

`@first`	随机生成一个常见的英文名

`@last`	随机生成一个常见的英文姓

`@name`	随机生成一个常见的英文姓名

`@cfirst`	随机生成一个常见的中文名

`@clast`	随机生成一个常见的中文姓

`@cname`	随机生成一个常见的中文姓名

## 颜色变量
`@color`	随机生成颜色，格式为 '#RRGGBB'

`@rgb`	随机生成颜色，格式为 'rgb(r, g, b)'

`@rgba`	随机生成颜色，格式为 'rgba(r, g, b, a)'

`@hsl`	随机生成颜色，格式为 'hsl(h, s, l)'

## 文本变量
`@paragraph`	随机生成一段文本

`@cparagraph	`随机生成一段中文文本

`@sentence`	随机生成一个句子，第一个单词的首字母大写

`@csentence`	随机生成一个中文句子

`@word`	随机生成一个单词

`@cword`	随机生成一个汉字

`@title`	随机生成一个标题

`@ctitle`	随机生成一个中文标题

## 正则表达式
`@regexp(input,regexp)`	根据正则表达式返回结果,表达式需要加引号''

## 支持组合函数 @word@title@title 这种连续拼接方式，组合函数中间尽量不要出现特殊字符