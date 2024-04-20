<script setup >
import cartShopping from '~/assets/image/shape/cart-shopping.png'

const data = ref({
  menu:[
    {
      name:"خانه",
      link:"/"
    },
    {
      name:"صفحه ها",
      link:"/"
    },
    {
      name:"دوره ها",
      link:"/"
    },
    {
      name:"صفحه های بلاگ" ,
      link:"/"
    },
    {
      name:"صفحه های تماس",
      link:"/"
    },
  ],
  brand:"همیشه بهار" ,
  icon:{
    shop:cartShopping ,
    search:""
  },
  start:"استارتش رو بزن ."
})
</script>

<template>
<div class="header">
  <div class="  mx-auto flex justify-between  items-center md:items-baseline px-5 sm:px-7 lg:px-0 py-5 gap-10 md:gap-x-16 lg:container header-container">
      <div class="navbar-brand"><nuxt-link to="/">{{data.brand}}</nuxt-link></div>
      <nav class="flex justify-between  lg:w-full items-center">
        <ul class="nav menu hidden justify-between gap-3 md:gap-10 lg:flex" >
          <li class="nav-item" v-for="(item , index) in data.menu" :key="index">
            <nuxt-link class="nav-link flex items-center gap-3"  :to="item.link">
              <span>{{item.name}}</span>
              <span><i class="fa-solid fa-angle-down fa-md text-sm font-bold"/></span>
            </nuxt-link>
          </li>
        </ul>
        <ul class="nav flex gap-6 items-center">
          <li class="nav-item search">
            <div class="input-group">
              <input type="text" id="search" placeholder="دنبال چی میگردی ؟">
              <label for="search" class="flex items-center justify-center" ><i class="fa-solid fa-search"/></label>
            </div>
          </li>
          <li class="nav-item shop-box  ">
            <nuxt-link class="nav-link hidden md:block" to="">
              <figure>
                <img :src="data.icon.shop" alt="cart-shopping">
              </figure>
            </nuxt-link>
          </li>
          <li class="nav-item start hidden md:inline-block">
            <nuxt-link class="nav-link" to="">{{ data.start }}</nuxt-link>
          </li>
        </ul>
      </nav>
  </div>
</div>
</template>

<style scoped lang="scss">
@import "~/assets/style/_varible.scss";
.header{
  position: relative;
  z-index: 1;
  &::before{
    content:url('~/assets/image/shape/right-cloud.png');
    position: absolute;
    top: 0;
    right: 0;
    z-index: -1 !important;
  }
  &:after{
    content:url('~/assets/image/shape/left-cloud.png');
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1 !important;
  }
}
.navbar-brand {
  text-wrap: nowrap;
}
nav {
  & .nav.menu {
    font-size: $fontSize-header-menu ;
  }
}
.input-group{
  display: flex;
  input{
    border: 1px solid rgba(0 ,0 ,0 , 0.1);
    border-left: 0;
    padding: 8px;
    border-top-right-radius:  3px;
    border-bottom-right-radius:  3px;
    outline: 0;
  }
  label{
    border: 1px solid rgba(0 ,0 ,0 , 0.1);
    border-right: 0;
    padding: 8px;
    border-top-left-radius:  3px;
    border-bottom-left-radius:  3px;
  }
}
.shop-box {
  display: flex;
  justify-content: center;
  align-items: center;
 & figure{
  width: 30px;
  height: 30px;
  & img{
    width: 100%;
    height: 100%;
  }
}
}
.start{
  background: $button-header;
  padding: 0.5rem 1rem;
  border-radius:  25px 0 25px 0;
  transition: 0.3s;
  color: $text-color-white-header;
  &:hover{
    background-color: $linearGradient-header-button !important;
    border-radius:0 25px 0 25px ;
    transition: 0.3s;
  }
}
@media screen and (max-width: 1450px) {
  .header{
    &::after,&::before{
      display: none !important;
    }
  }
};
@media screen and (max-width: 500px) {
  .input-group{
    display: flex;
    input{
      display: none !important;
    }
    label{
      border: 0;
      padding: 8px;
    }
  }
  .shop-box{
    display: none;
  }
};
</style>