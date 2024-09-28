//定义食物类
class Food {
    //定义一个属性表示食物所对应的元素
    element: HTMLElement;
    constructor() {
        //获取页面中food元素赋值给element
        this.element = document.getElementById("food")!;
    }

    //获取食物x轴坐标
    get x() {
        return this.element.offsetLeft
    }
    //获取食物y轴坐标
    get y() {
        return this.element.offsetTop
    }
    //修改食物位置
    change() {
        //生成随机位置
        //食物的位置最小是0，最大是290
        //蛇每次移动20，所以食物坐标必须是整20
       let top= Math.round(Math.random() * 14)*20
       let left= Math.round(Math.random() * 14)*20
        this.element.style.left =left+"px";
        this.element.style.top =top+"px";
    }
}
export default Food