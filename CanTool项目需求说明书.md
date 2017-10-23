<<<<<<< HEAD
# “CanToolApp”项目需求文档 #
=======
# “CanToolApp ”项目需求文档 #
>>>>>>> 325c8da4e8930b569ae873c1e3d608442353f185
一、项目介绍
1.1项目背景介绍

在现代汽车控制技术中，汽车中会使用多个电子控制装置（ECU：Electronic Control Unit）对整车进行控制。而ECU之间的信息交换更多地依赖于CAN(Controller Area Network)总线的网络连接方式来完成。为了检测和控制CAN bus的信息内容，需要使用CAN bus检测设备。CanTool装置是完成CANbus检测的工具。为了实现CAN数据的显示及控制，需要使用本文提出的CanToolApp软件。该软件需要将连接在CAN总线上的CanTool装置采集的CAN信息发送到上位机（移动终端Android、iOS、Windows PC），并由运行在上位机中的CanToolApp软件接收这些信息，显示在用户图形界面上。同时在CanToolApp的界面上还可以设定CAN信息，通过GUI按钮将信息发送给CanTool装置，CanTool装置将按照规定的信息格式，将信息发送的CAN总线上。

此外，CanToolApp可以设定CAN总线的通信参数，并通过相应的命令设置CanTool装置的CAN通信参数，以使CanTool装置能够与CAN总线上的其他被测ECU进行正常的通信。系统的总体框图见图 1所示。CanTool装置与上位机通过USB串口或蓝牙RFComm实现UART串口通信。与上位机连接的串口号需要用户选择，串口的波特率固定为115200BPS，8个数据位，1个停止位。
![](https://i.imgur.com/ZWB2vuX.png)

图 1 CanTool系统框图

1.2用户

汽车制造、检测人员

1.3参考资料

《构建之法》、Android APP：BlueTooth Chat、CAN bus（https://en.wikipedia.org/wiki/CAN_bus）
	

1.4开发环境

ADT+eclipse+SDK


二：可行性分析

2.1经济可行性

随着时代发展，汽车已经成为了大多数人的代步工具。而CanTool及CanToolApp系统是现代汽车控制技术中不可缺少的一部分。因此，在汽车产业越来越繁荣的背景下，CanTool项目应有较大的经济支持。再加上手机的广泛使用，CanToolAPP的开发显得尤为重要，在手机上可以看到汽车的一些数据。

2.2技术可行性

实现CanTool和CanToolApp的硬件设施、软件设置（如：windows、Android）都已非常成熟。现在Android系统已经很成熟


三：功能需求分析
软件的主要模块如下所示：
![](https://i.imgur.com/lbYfb5I.png)


3.1、CanTool装置

- 采集CAN总线上的信息

- 给CAN总线发送扩展帧命令

- 将装置信息发送给CanToolApp


3.2、CanToolApp

- 能够通过手机的蓝牙设备与CanTool装置进行连接，实现数据传输
 
- 能够实现CANtool装置的CAN速率设置、进入CAN工作状态（Open）、进入CAN初始化状态（ Close）。这些设定内容可保存到CanToolApp设定文件中，供下次使用。

- 能够对接收到的多个CAN信息，通过CAN信息及CAN信号数据库进行解析，将CAN信息原始数据进行显示。并能对CAN信息中的CAN信号的物理值实时数据进行显示。

- 显示时可以让用户选择仪表盘方式显示接收到CAN信号物理值。这些用户选择的显示方式可保存到CanToolApp设定文件中，供下次使用。仪表盘样式如图 7所示。仪表盘、LED等需要自制控件。
![](https://i.imgur.com/VAQHk9O.png)

- 可以让用户选择某些接收到的CAN信号，显示其变化的实时物理值曲线。
- 可以将接收到的所有CAN信息数据，实时保存为数据文件。格式为CSV格式，或自定义。

- 能够指定要发送的多个CAN信息，并允许用户设定CAN信息中的CAN信号物理值。可以指定CAN信息的发送周期（0-65535ms即0x0000-0xFFFF）。

- App可将用户设定的物理值转换为CAN信号值，将CAN信息中包含的所有CAN信号合成完整的CAN信息后，发送给CanTool装置，发送到CAN总线上。
 
- 可以将用户提供的CAN信息和信号数据库另存为xml和JSON (JavaScript Object Notation)格式。也可以已将xml或Json格式的数据库，转换为CAN信息和信号数据库格式。

- 可以将所有CAN信息实时数据、CAN设定信息等 通过WEB API方式更新到远程数据库。此时CanToolApp作为客户端与远程的Web API服务进行数据交换。此功能需要完成WebAPI服务器端的get\post等服务，实现数据的增删改查、可视化数据显示，实现数据共享。（此功能可以独立一个项目完成）。

- 功能可能随时增加或修改，需要做好变更管理。


五、术语定义

5.1、CAN bus

	Controller Area Network. A Controller Area Network (CAN bus) is a robust vehicle bus standard designed to allow microcontrollers and devices to communicate with each other in applications without a host computer. It is a message-based protocol, designed originally for multiplex electrical wiring within automobiles to save on copper, but is also used in many other contexts. 
	Reference URL：https://en.wikipedia.org/wiki/CAN_bus

5.2、ECU

	Electronic Control Unit. A control device used in vehicle.

5.3、CanTool装置，CanToolApp

	用于CANBUS的CAN信息采集与发送的装置，CanToolApp用于上位机与CanTool装置进行通信，并完成CAN信息、信号的显示与设定。
	
5.4、CAN message
	
	CAN message 由CAN id，dlc，data构成

5.5、CAN signal

	Can Signal是分布在CAN message中的CAN信号。具有一定物理意义。

5.6、CAN信息和信号数据库

	用于存储CAN信息的组成信息，和CAN信号的相关参数设置

5.7、Little endian / Big Endian
	
	数据在存储空间中保存的方式。

5.8、WebAPI

	A web API is an application programming interface (API) for either a web server or a web browser. It is a web development concept, usually limited to a web application's client-side (including any web frameworks being used), and thus usually does not include web server or browser implementation details ~ 4 ~such as SAPIs or web browser engine APIs unless publicly accessible by a remote web application..
