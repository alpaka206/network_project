<template>
  <div class="black-bg" v-if="역삼동 == 1">
    <div class="white-bg" >
      <h4>역삼동 원룸</h4>
      <p>따뜻함</p>
      <button @click = "역삼동 = false">닫기</button>
    </div>
  </div>
  <div class="black-bg" v-if="역곡 == 1">
    <div class="white-bg" >
      <h4>역곡 원룸</h4>
      <p>학교와 가까움</p>
      <button @click = "역곡 = false">닫기</button>
    </div>
  </div>
  <div class="black-bg" v-if="마포구 == 1">
    <div class="white-bg" >
      <h4>마포구 원룸</h4>
      <p>하얌</p>
      <button @click = "마포구 = false">닫기</button>
    </div>
  </div>

  <div class="black-bg" v-if="방1 == 1">
    <div class="white-bg" >
      <h4>방1</h4>
      <p>따뜻함</p>
      <button @click = "방1 = false">닫기</button>
    </div>
  </div>
  <div class="black-bg" v-if="방2 == 1">
    <div class="white-bg" >
      <h4>방2</h4>
      <p>학교와 가까움</p>
      <button @click = "방2 = false">닫기</button>
    </div>
  </div>
  <div class="black-bg" v-if="방3 == 1">
    <div class="white-bg" >
      <h4>방3</h4>
      <p>하얌</p>
      <button @click = "방3 = false">닫기</button>
    </div>
  </div>


  <div class="menu">
    <a v-for="(a,i) in 메뉴" :key="i">{{a}}</a>
  </div>

  <!-- <div v-for="(a,i) in products" :key="i"> 
    <h4 :style="스타일">{{a}}</h4>
    <p> {{ price[i] }} 만원</p>
    <button  @mouseover="increase" @click="신고수[i]++">허위매물신고</button> <span>신고수 : {{신고수[i]}}</span>
  </div> -->
  <div>
    <img :src="원룸[0].image" class="room-img" @click="역삼동=true">
    <h4 @click="역삼동=true">{{원룸[0].title}}</h4>
    <p>{{원룸[0].price}} 만원</p>
    <button @click="신고수[0]++">허위매물신고</button> <span>신고수 : {{신고수[0]}}</span>
  </div>
  <div>
    <img :src="원룸[1].image" class="room-img" @click="역곡=true">
    <h4 @click="역곡=true">{{원룸[1].title}}</h4>
    <p>{{ price2 }} 만원</p>
     <button @click="신고수[1]++">허위매물신고</button> <span>신고수 : {{신고수[1]}}</span>
  </div>
  <div>
    <img :src="원룸[2].image" class="room-img" @click="마포구=true">
    <h4 @click="마포구=true">{{원룸[2].title}}</h4>
    <p>{{ price3 }} 만원</p>
     <button @click="신고수[2]++">허위매물신고</button> <span>신고수 : {{신고수[2]}}</span>
  </div>

  <div>
    <img :src="원룸[3].image" class="room-img" @click="방1=true">
    <h4 @click="역삼동=true">{{원룸[3].title}}</h4>
    <p>{{원룸[0].price}} 만원</p>
    <button @click="신고수[3]++">허위매물신고</button> <span>신고수 : {{신고수[3]}}</span>
  </div>
  <div>
    <img :src="원룸[4].image" class="room-img" @click="방2=true">
    <h4 @click="역곡=true">{{원룸[4].title}}</h4>
    <p>{{ price2 }} 만원</p>
     <button @click="신고수[4]++">허위매물신고</button> <span>신고수 : {{신고수[4]}}</span>
  </div>
  <div>
    <img :src="원룸[5].image" class="room-img" @click="방3=true">
    <h4 @click="마포구=true">{{원룸[5].title}}</h4>
    <p>{{ price3 }} 만원</p>
     <button @click="신고수[5]++">허위매물신고</button> <span>신고수 : {{신고수[5]}}</span>
  </div>
</template>

<script>

import data from './assets/oneroom.js'; 


export default {
  name: 'App',
  data(){
    return {
      원룸 : data,
      역삼동 : 0,
      역곡 : 0,
      마포구 : 0,
      방1 : 0,
      방2 : 0,
      방3 : 0,
      신고수 : [0,0,0,0,0,0],
      price1 : 50,
      price2 : 60,
      price3 : 70,
      price : [50,60,70],
      products : ['역삼동원룸','역곡원룸','마포구원룸'],
      스타일 : 'color : blue',
      메뉴 : ['Home','Products','About'],
    }
  },
  methods : {
    increase0(){
      this.신고수[0]++;
    }
  },
  components:{

  }
}
</script>

<style>
body{
  margin : 0
}
div{
  box-sizing: border-box;
}
.black-bg {
  width: 100%; height: 100%;
  background: rgba(0,0,0,0.5);
  position: fixed; padding: 20px;
}
.white-bg {
  width: 100%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}


.room-img{
  width: 100%;
  margin-top:40px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
}
.menu {
  background: darkslateblue;
  padding: 15px; 
  border-radius: 5px;
}
.menu a{
  color : white;
  padding:10px;
}
</style>
