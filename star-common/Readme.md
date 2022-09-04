# 算法优缺点
## 雪花算法有以下几个优点：
```
高并发分布式环境下生成不重复 id，每秒可生成百万个不重复 id。
基于时间戳，以及同一时间戳下序列号自增，基本保证 id 有序递增。
不依赖第三方库或者中间件。
算法简单，在内存中进行，效率高。
```
## 雪花算法有如下缺点：

```
依赖服务器时间，服务器时钟回拨时可能会生成重复 id。算法中可通过记录最后一个生成 id 时的时间戳来解决，每次生成 id 之前比较当前服务器时钟是否被回拨，避免生成重复 id。
注意事项
其实雪花算法每一部分占用的比特位数量并不是固定死的。例如你的业务可能达不到 69 年之久，那么可用减少时间戳占用的位数，雪花算法服务需要部署的节点超过1024 台，那么可将减少的位数补充给机器码用。

注意，雪花算法中 41 位比特位不是直接用来存储当前服务器毫秒时间戳的，而是需要当前服务器时间戳减去某一个初始时间戳值，一般可以使用服务上线时间作为初始时间戳值。

对于机器码，可根据自身情况做调整，例如机房号，服务器号，业务号，机器 IP 等都是可使用的。对于部署的不同雪花算法服务中，最后计算出来的机器码能区分开来即可。
```