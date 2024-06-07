<script setup lang="ts">

</script>

<template>
  <div class="header-pages h-[25vh] flex justify-center items-center overflow-hidden relative">
    <div class="inner-container relative w-full h-full flex items-center justify-center overflow-hidden">
      <div class="content flex flex-col items-center justify-center gap-4">
        <div class="title ">
          <h3>صفحه معرفی مربی و مدرسین </h3>
        </div>
        <div class="breadcrumb">
          <lazy-breadcrumb/>
        </div>
      </div>
      <div class="shape-move shape-blue hidden lg:block"><img src="~/assets/image/shape/blue-dot.png" alt="shape-move"></div>
      <div class="shape-move shape-wave hidden lg:block"><img src="~/assets/image/shape/wave.png" alt="shape-move"></div>
      <div class="shape-move shape-square hidden lg:block "><img src="~/assets/image/shape/red-square.png" alt="shape-move"></div>
      <div class="image-circle-left hidden lg:block "></div>
      <div class="image-circle-right hidden lg:block "></div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.header-pages {
  background: #F5F9FA;
}

.title {
  font-size: 30px;
  font-weight: 700;
}

.image-circle-left {
  position: absolute;
  width: 350px;
  height: 350px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  top: -10%;
  transform: translate(-50%, -50%);
  left: 7%;
  border-radius: 100%;
}

.image-circle-right {
  position: absolute;
  width: 350px;
  height: 350px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  bottom: 5%;
  transform: translate(50%, 50%);
  right: 5%;
  border-radius: 100%;
}
.shape-blue{
  position: absolute;
  bottom: 0;
  left: 5%;
  img{
    width: 80%;
  }
}
.shape-wave{
  position: absolute;
  bottom: 40%;
  right: 7%;
  transform: translate(-50%, 50%);
  img{
    width: 80%;
  }
}
.shape-square{
  position: absolute;
  top: -15%;
  right: -3%;
  img{
    width: 80%;
  }
}
</style>