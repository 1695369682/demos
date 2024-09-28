//定义表示积分的类
class score_level{
    score=0;
    level=1;
    //设置变量限制等级
    max_level:number
    //升级所需积分
    upscore:number
    //socre和level所咋在的元素
    scorele:HTMLElement
    levelent:HTMLElement
    constructor(max_level:number=10,upscore:number=2){
        this.scorele=document.getElementById("score")!
        this.levelent=document.getElementById("level")!

        this.max_level=max_level
        this.upscore=upscore
    }

    //加分
    addscore(){
        this.score++
        this.scorele.innerHTML="score:"+this.score
        //判断分数多少
        if(this.score%this.upscore===0){
            this.addlevel()
        }
    }
    //加等级
    addlevel(){
        if(this.level<this.max_level)
        this.level++
        this.levelent.innerHTML="level:"+this.level
    }
}
export default score_level