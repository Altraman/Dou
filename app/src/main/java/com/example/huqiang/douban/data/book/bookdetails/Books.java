package com.example.huqiang.douban.data.book.bookdetails;

import com.example.huqiang.douban.data.Images;
import com.example.huqiang.douban.data.book.Rating;
import com.example.huqiang.douban.data.book.Tags;

import java.util.List;

/**
 * Created by HuQiang on 2017/7/25.
 */

public class Books {

    /**
     * rating : {"max":10,"numRaters":109,"average":"6.5","min":0}
     * subtitle : 引领未来的用户界面开发框架
     * author : ["卓越开发者联盟"]
     * pubdate : 2015-5-1
     * tags : [{"count":74,"name":"React","title":"React"},{"count":62,"name":"JavaScript","title":"JavaScript"},{"count":47,"name":"前端开发","title":"前端开发"},{"count":21,"name":"前端","title":"前端"},{"count":21,"name":"Web前端","title":"Web前端"},{"count":12,"name":"react","title":"react"},{"count":11,"name":"计算机","title":"计算机"},{"count":10,"name":"编程","title":"编程"}]
     * origin_title : Developing a React Edge: The JavaScript Library for User Interfaces
     * image : https://img1.doubanio.com/mpic/s28061237.jpg
     * binding : 平装
     * translator : ["寸志","范洪春","杨森","陈涌"]
     * catalog : 推荐序1 . . . . . . . iii 推荐序2 . . . . . . . v 推荐序3 . . . . . . . vii 前言. . . . . . . . . . . ix 第1 章React 简介. . . . . . . . . . . . . . . . . . . . . . . 1 背景介绍1 本书概览3 Component 的创建和复合3 进阶4 React 工具5 React 实践5 第2 章JSX . . . . 6 什么是JSX 7 使用JSX 的好处7 更加熟悉7 更加语义化8 更加直观8 抽象化9 关注点分离9 复合组件10 定义一个自定义组件10 使用动态值11 子节点12 JSX 与HTML 有何不同13 属性13 条件判断14 非DOM 属性15 事件17 注释18 特殊属性19 样式19 没有JSX 的React 19 创建React 元素20 简写21 延伸阅读及参考引用21 JSX 官方规范22 第3 章组件的生命周期. . . . . . . . . . . . . . . . . 23 生命周期方法23 实例化23 存在期24 销毁& 清理期24 实例化24 getDefaultProps 24 getInitialState 25 componentWillMount 25 render 25 componentDidMount 25 存在期26 componentWillReceiveProps 26 shouldComponentUpdate 27 componentWillUpdate 28 componentDidUpdate 28 销毁& 清理期28 componentWillUnmount 28 反模式：把计算后的值赋给state 28 总结30 第4 章数据流. 31 Props 31 PropTypes 33 getDefaultProps 33 State 34 放在state 和props 的各是哪些部分35 总结35 第5 章事件处理. . . . . . . . . . . . . . . . . . . . . . . . 36 绑定事件处理器36 事件和状态37 根据状态进行渲染38 更新状态40 事件对象42 总结43 第6 章组件的复合. . . . . . . . . . . . . . . . . . . . . . 44 扩展HTML 44 组件复合的例子45 组装HTML 45 追踪状态47 整合到父组件当中48 父组件、子组件关系50 总结52 第7 章mixin . . 53 什么是mixin 53 总结56 第8 章DOM 操作. . . . . . . . . . . . . . . . . . . . . . . 57 访问受控的DOM 节点57 整合非React 类库59 侵入式插件61 总结63 第9 章表单. . . 64 无约束的组件65 约束组件66 表单事件68 Label 68 文本框和Select 69 复选框和单选框71 表单元素的name 属性73 多个表单元素与change 处理器75 自定义表单组件79 Focus 83 可用性83 把要求传达清楚83 不断地反馈84 迅速响应84 符合用户的预期84 可访问85 减少用户的输入85 总结86 第10 章动画. . 87 CSS 渐变组87 给渐变class 添加样式88 渐变生命周期89 使用渐变组的隐患89 间隔渲染89 使用requestAnimationFrame 实现间隔渲染90 使用setTimeout 实现间隔渲染91 总结92 第11 章性能优化. . . . . . . . . . . . . . . . . . . . . . . 93 shouldComponentUpdate 93 不可变性辅助插件95 深入调查拖慢你应用的部分96 键（key） 97 总结98 第12 章服务端渲染. . . . . . . . . . . . . . . . . . . . . 99 渲染函数100 React.renderToString 100 React.renderToStaticMarkup 100 用React.renderToString 还是用React.renderToStaticMarkup 101 服务端组件生命周期102 设计组件102 异步状态104 同构路由106 单例、实例及上下文107 总结107 第13 章周边类库. . . . . . . . . . . . . . . . . . . . . . . 108 Jest 108 设置109 自动模拟依赖109 手动模拟依赖111 Immutable.js 113 Flux 114 总结115 第14 章开发工具. . . . . . . . . . . . . . . . . . . . . . . 116 构建工具116 Browserify 117 建立一个Browserify 项目117 对代码做出修改118 Watchify 119 构建119 Webpack 119 Webpack 与React 120 调试工具122 基础工具123 总结124 第15 章测试. . 125 上手125 测试的类型126 工具126 第一个测试用例：render 测试127 模拟组件132 函数监视138 监视函数被调用141 模拟事件146 测试中的组件查找器149 mixin 测试152 直接测试mixin 153 把mixin 包含在虚拟组件中进行测试156 共享行为的用例159 渲染到<body> 中164 服务端测试168 浏览器自动化测试174 启动服务器179 总结180 第16 章架构模式. . . . . . . . . . . . . . . . . . . . . . . 181 路由182 Backbone.Router 182 Aviator 183 react-router 185 Om (ClojureScript) 186 Flux 187 数据流187 Flux 各个部分188 Dispatcher 188 Action 189 Store 190 控制视图191 管理多个Store 192 更新Dispatcher 192 注册依赖行为193 总结194 第17 章其他使用场景. . . . . . . . . . . . . . . . . . 195 桌面应用195 游戏197 电子邮件202 绘图208 总结210
     * pages : 224
     * images : {"small":"https://img1.doubanio.com/spic/s28061237.jpg","large":"https://img1.doubanio.com/lpic/s28061237.jpg","medium":"https://img1.doubanio.com/mpic/s28061237.jpg"}
     * alt : https://book.douban.com/subject/26378583/
     * id : 26378583
     * publisher : 电子工业出版社
     * isbn10 : 7121259362
     * isbn13 : 9787121259364
     * title : React
     * url : https://api.douban.com/v2/book/26378583
     * alt_title : Developing a React Edge: The JavaScript Library for User Interfaces
     * author_intro : 本书由一个团队编写而成，这个团队的成员都是一些经验丰富且专注于JavaScript 的开发者。 Tom Hallett 是一位高级Ruby 和JavaScript 工程师，在Tout.com 工作（Tout.com 是一个实时视频平台，办公地点在旧金山）。他是jasmine-react 的作者，jasmine-react 是一个开源的类库，旨在帮助开发者使用测试框架Jasmine 测试React 应用程序。在Twitter（@tommyhallett）和Github（@tommyh）上都可以找到他。他的爱好是打水球，以及与妻子和儿子待在一起。 Richard Feldman 是旧金山教育科技公司NoRedInk 的前端工程师。他是一个函数式编程爱好者，会议发言人，还是seamlessimmutable的作者。seamless-immutable 是一个开源类库，可以提供不可变的数据结构，向后兼容普通的JavaScript 对象和数组。Richard 在Twitter 和Github 上都叫@rtfeldman。 Simon H?jberg 是一个高级UI 工程师，在罗德岛普罗维登斯市的Swipely 公司工作。他是普罗维登斯市线下JS 见面会的核心组织者，之前还是波士顿创业学院的JavaScript 讲师。他一直在使用JavaScript 开发功能性的用户界面，也会开发一些像cssarrowplease.com 这样的业余项目。Simon 的Twitter 是@shojberg。 Karl Mikkelsen 是LockedOn 的一位高级PHP 和JavaScript 工程师，工作是开发外观漂亮且功能强大的房地产软件。Karl 对新技术充满热情，喜欢学习以不同的方式做事。如果你在网上（http://karlmikko.com）找不到他，那他很可能在和妻子攀岩或者在喝咖啡。 Jon Beebe 在Dave Ramsey 的数字开发团队里开发应用，专注于一些面向用户的技术，例如Web 和iOS。在这之前，他开发过PHPWeb 服务，也为Final Cut Pro 和Motion 写过插件。他以能够把艺术和代码结合到一起为乐。他的网名是@bejonbee。他自诩是一个热衷阅读的人，喜欢摄影，并且以超出妻子的日常期望为自己的目标。 Frankie Bagnardi 是一位高级前端工程师，为多种不同的客户端创造用户体验。在业余时间里，他会在StackOverflow（FakeRainBrigand）和IRC（GreenJello）上回答问题，或者开发一些小项目。你可以通过f.bagnardi@gmail.com 联系他。
     * summary : 2014 年横空出世的由Facebook 推出的开源框架React.js，基于Virtual DOM 重新定义了用户界面的开发方式，彻底革新了大家对前端框架的认识，将PHP 风格的开发方式迁移到客户端应用开发。其优势在于可以与各种类库、框架搭配使用。《React：引领未来的用户界面开发框架》是这一领域的首作，由多位一线专家精心撰写，采用一个全程实例全面介绍和剖析了ReactReact.js 的方方面面，适合广大前端开发者、设计人员，及所有对未来技术趋势感兴趣者阅读。
     * price : CNY 65.00
     */

    private Rating rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private Images images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    private List<Tags> tags;
    private List<String> translator;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }
}
