class snack{
    //获取蛇头元素
    head:HTMLElement
    //身体（包括蛇头
    bodys:HTMLCollection
    element:HTMLElement

    constructor(){
        this.head=document.querySelector("#snack>div") as HTMLElement
        this.bodys=document.getElementById("snack")!.getElementsByTagName("div")
        this.element=document.getElementById("snack")!
    }
    //获取蛇坐标
    get x(){
        return this.head.offsetLeft
    }
    //获取蛇坐标
    get y(){
        return this.head.offsetTop
    }

    //设置蛇头坐标
    set x(value:number){
        if(this.x==value){return} 
        if(value<0||value>290){
            //说明蛇撞墙了
            throw new Error("我是傻波依")
        }
        // 修改x时，是在修改水平坐标，蛇在左右移动，蛇在向左移动时，不能向右掉头，反之亦然
        if(this.bodys[1] && (this.bodys[1] as HTMLElement).offsetLeft === value){
            // console.log('水平方向发生了掉头');
            // 如果发生了掉头，让蛇向反方向继续移动
            if(value > this.x){
                // 如果新值value大于旧值X，则说明蛇在向右走，此时发生掉头，应该使蛇继续向左走
                value = this.x - 20;
            }else{
                // 向左走
                value = this.x + 20;
            }
        }
        this.movebody()
        this.head.style.left=value+"px"
        this.checkHeadBody()
    }
    set y(value:number){
        if(this.y==value){return}
        //检查是否撞墙
        if(value<0||value>290){
            //说明蛇撞墙了
            throw new Error("我是大傻波依")
        }
        // 修改y时，是在修改垂直坐标，蛇在上下移动，蛇在向上移动时，不能向下掉头，反之亦然
        if(this.bodys[1] && (this.bodys[1] as HTMLElement).offsetTop === value){
            if(value > this.y){
                value = this.y - 20;
            }else{
                value = this.y + 20;
            }
        }
        this.movebody()
        this.head.style.top=value+"px"
        this.checkHeadBody()
    }

    //蛇吃东西，增加身体
    addbody(){
        this.element.insertAdjacentHTML("beforeend","<div></div>")
    }
    //移动身体
    movebody(){
        /*
        *   将后边的身体设置为前边身体的位置
        *       举例子：
        *           第4节 = 第3节的位置
        *           第3节 = 第2节的位置
        *           第2节 = 蛇头的位置
        * */
        // 遍历获取所有的身体
        for(let i=this.bodys.length-1; i>0; i--){
            // 获取前边身体的位置
            let X = (this.bodys[i-1] as HTMLElement).offsetLeft;
            let Y = (this.bodys[i-1]as HTMLElement).offsetTop;
            // 将值设置到当前身体上
            (this.bodys[i] as HTMLElement).style.left = X + 'px';
            (this.bodys[i] as HTMLElement).style.top = Y + 'px';

        }
    }
     // 检查蛇头是否撞到身体的方法
     checkHeadBody(){
        // 获取所有的身体，检查其是否和蛇头的坐标发生重叠
        for(let i=1; i<this.bodys.length; i++){
            let bd = this.bodys[i] as HTMLElement;
            if(this.x === bd.offsetLeft && this.y === bd.offsetTop){
                // 进入判断说明蛇头撞到了身体，游戏结束
                throw new Error('撞到自己了！');
            }
        }
    }
}
export default snack