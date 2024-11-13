<script setup lang="ts">
import {useFaqStore} from "~/store/front/faq";

const collapses = ref(null)
const icons = ref(null)
const storeFaq = useFaqStore()
const faqData = computed(() => storeFaq.getFaq)
const colors = [
  "#8E56FF",
  "#F8941F",
  "#1AB69D"
]
storeFaq.fetchingFAQ()
function show(index) {
  if (collapses.value[index].style.maxHeight) {
    collapses.value[index].style.maxHeight = null
  } else {
    removeCollapseShow()
    collapses.value[index].style.maxHeight = `${collapses.value[index].scrollHeight}px`
    icons.value[index].classList.add('rotate-angle')
  }
}

function removeCollapseShow() {
  icons.value.forEach(item => item.classList.remove('rotate-angle'))
  collapses.value.forEach((item) => {
    item.style.maxHeight = null
  })
}


console.log(faqData.value)
</script>

<template>
  {
  <div v-if="faqData && faqData.length" class="accordion-collapse education-card flex flex-col gap-5" data-aos="zoom-in">
    <div class="collapse-container " v-for="(item,index) in faqData" :key="index">
      <div class="button text-red-600 flex justify-between px-5 py-4 cursor-pointer"
           :style="`background:${colors[index]}`"
           @click="show(index)">
        <h3>{{ item.title }}</h3>
        <span><i ref="icons" class="fa-solid fa-angle-down transition duration-300 ease-linear"></i></span>
      </div>
      <div class="text  " ref="collapses">
        <p class="p-3">
          {{ item.text }}
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.button {
  color: #fff;
  border-radius: 5px;
}

.text {
  overflow: hidden;
  transition: 0.5s;
  max-height: 0;

  p {
    background-color: #FFFFFF;
  }
}


.show i {
  transform: rotate(180deg);
}

.text::-webkit-scrollbar {
  display: none;
}

i {
  transition: 0.2s ease;
}

.rotate-angle {
  transform: rotate(180deg);

}

</style>
