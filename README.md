# Phi-Util

基于Mirai Console，使用 Kotlin + Gradle 构建的 Phigros 辅助工具。

**特别感谢 Phigros 谱面定数测算组提供的准确的数据！**

> 大家也可以用用看定数组的rks计算表(OwO)

## 使用说明

该插件监听群聊消息，考虑到安全问题（bot和太多陌生人私聊很可能会被风控）暂时不支持私聊查询。

- 查询定数：`定数查询 曲名`
- 计算rks：`在做了在做了（咕`

## 构建

使用`gradle`任务`gradle buildPlugin`可以在`build/mirai/`下得到目标Jar包。生成的文件直接丢进mcl的plugins目录下即可使用。

## TODO

- 行数控制：行数过长时私发
- 修改查询指令：`定数查询 曲名`以减少不必要消息