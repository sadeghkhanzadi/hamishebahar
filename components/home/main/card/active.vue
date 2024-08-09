<script setup lang="ts">
const props = defineProps(['data'])
const counter = ref(10)

console.log(counter.value += props.data.length)
const image = ref(null)

function hover() {
  image.value.style.background = props.data.color
  // image.value.style.filter = `opacity(1)`
}

function leave() {
  image.value.style.background = props.data.bg
  // image.value.style.filter = `opacity(0.5)`

}
</script>

<template>
  <div>
    <div class="content-container" data-aos="fade-up">
      <div class="card flex gap-5">
        <div class="card-header flex">
          <div class="image flex justify-center items-center backdrop-opacity-50 hover:text-white"
               :style="{'--after':props.data.color ,background:props.data.bg ,color:props.data.color }" ref="image" @mouseover="hover"
               @mouseout="leave">
            <span><i :class="`fa-solid fa-${props.data.icon} fa-xl `"/></span>
          </div>
        </div>
        <div class="card-body flex flex-col">
          <nuxt-link to="">
            <div class="title text-end">
              <h3>{{ props.data.name }}</h3>
            </div>
            <div class="text text-end">
              <p>{{ props.data.text }}</p>
            </div>
          </nuxt-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '@/assets/style/_varible.scss';
:root{
  --after:red;
}
h3 {
  font-family: $fontFamily-title-activities;
  font-size: $fontSize-title-activities;
  font-weight: $fontWeight-title-activities;
  color: $color-title-activities;
}

p {
  font-size: $fontSize-text-activities;
  color: $color-text-activities;
}

.image {
  position: relative;
  width: 120px;
  height: 89px;
  mask-image: url("@/assets/image/shape/card-cloud.png");
  mask-repeat: no-repeat;
  mask-position: center;

  &:after {
    content: "";
    //width: 100%;
    //height: 100%;
    position: absolute;
    mask-image: url("@/assets/image/shape/card-cloud-border.png");
    mask-repeat: no-repeat;
    mask-position: center;
    inset: 0;
    background-color: var(--after);
  }
  &:hover {
    color: #FFFFFF !important;
  }
}
</style>