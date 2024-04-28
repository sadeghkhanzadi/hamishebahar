<script setup lang="ts">

</script>

<template>
  <div class="news-container min-h-[40vh] md:min-h-[60vh]">
    <div class="news relative ">
      <div class="news-title text-center">
        <h3>
          از اخبار مربوط به مهدکودک مطلع شوید
          اخبار و رویداد ها!
        </h3>
      </div>
      <div class="get-email">
        <div class="input-group  mt-7 flex flex-col md:flex-row gap-5 items-center justify-center w-full">
          <input type="email" class="py-4 w-full px-3 rounded outline-0 text-sm "
                 placeholder="لطفا ایمیل خود را واردی کنید ">
          <button class="btn flex w-full md:w-auto gap-2 justify-center items-center py-4 px-7 text-sm text-white">
            <span>عضویت</span>
            <i class="fa-solid fa-arrow-right flex items-center text-white"/>
          </button>
        </div>
      </div>
      <div class="shapes hidden lg:block">
        <div class="shape shape-laboratory hidden lg:block"><span><img src="@/assets/image/shape/labratoar.png" alt=" shape-laboratory"></span>
        </div>
        <div class="shape shape-book hidden lg:block"><span><img src="@/assets/image/shape/book.png" alt="shape-book"></span></div>
        <div class="shape shape-boy shape-move hidden xl:block"><span><img src="@/assets/image/face/boy-news.webp" alt="shape-boy"></span></div>
        <div class="shape shape-girl shape-move hidden xl:block"><span><img src="@/assets/image/face/girl-news.webp" alt="shape-girl"></span></div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "@/assets/style/varible.scss";

.news-container {
  background-color: #F7EDE6;
  display: flex;
  justify-items: center;
  align-items: center;
  padding: 20px;
  position: relative;
  overflow: hidden;
  &:after {
    content: url("@/assets/image/shape/cloud-blue-sky.png");
    position: absolute;
    top: -10px;
    right: 0;
    transform: rotate(180deg);
  }
}

.news {
  max-width: 600px;
  margin: auto;

  h3 {
    font-weight: $fontWeight-title-news;
    font-size: $fontSize-title-news;
    font-family: $fontFamily-title-news;
    color: $color-title-news;
    line-height: $lineHeight-title-news;
  }
}

button {
  background: $linearGradiant-normal-button;
  border-radius: $border-radius-btn;
  transition: 0.3s;

  &:hover {
    background: $linearGradient-button;
    border-radius: $border-radius-btn-hover;
  }
}

.shape {
  position: absolute;
}
.shape-book{
  opacity: 0.5;
  bottom: -100px;
  left: -10%;
}
.shape-laboratory{
  opacity: 0.5;
  top: 0;
right: -20%;
}
.shape-girl{
left: -90%;
  top: -60%;
}
.shape-boy{
right: -90%;
  top: -60%;
}
</style>