//控制其他类
import snack from "./snack";
import Food from "./food";
import score_level from "./score_level";
class controler {
    //定义三个属性
    snack: snack;
    food: Food;
    score_level: score_level;
    //存储蛇的移动方向
    direction: string = ""
    //游戏结束条件
    isfina: boolean = true

    constructor() {
        this.snack = new snack()
        this.food = new Food()
        this.score_level = new score_level()
        this.init()
    }
    //游戏初始化,调用游戏即开始
    init() {
        document.addEventListener("keydown", this.keydown_handler.bind(this))
        this.run()
    }
    //键盘按下的相应方法
    keydown_handler(event: KeyboardEvent) {
        //检查是否按了上下左右

        //修改direction
        this.direction = event.key
    }
    //控制蛇移动的方法
    run() {
        //根据direction改变蛇的位置
        //获取蛇现在坐标
        let x = this.snack.x
        let y = this.snack.y

        switch (this.direction) {
            case "ArrowUp":
            /* 兼容ie的按键反馈 */
            case "up":
                y -= 20;
                break;
            case "ArrowDown":
            case "down":
                y += 20;
                break;
            case "ArrowLeft":
            case "Left":
                x -= 20;
                break;
            case "ArrowRight":
            case "Right":
                x += 20;
                break;
        }

        //检查蛇吃没吃到食物
        this.checkeat(x, y)

        try {
            //修改蛇的xy值
            this.snack.x = x
            this.snack.y = y
        } catch (e) {
            alert("==表示等于，=是赋值")
            console.log(e.message);
            //停止游戏
            this.isfina = false
        }


        //开启定时调用(蛇一直走)
        this.isfina && setTimeout(this.run.bind(this), 200 - 20 * (this.score_level.level - 1));
    }

    //检查蛇是不是吃到了食物
    checkeat(x: number, y: number) {
        if (x === this.food.x && y === this.food.y) {
            //食物换位
            this.food.change()
            //加分
            this.score_level.addscore()
            //蛇变长
            this.snack.addbody()
        }
    }
}
export default controler