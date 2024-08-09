<script setup lang="ts">
const props = defineProps(['data'])
const collapses = ref(null)
function show(index) {
  if (collapses.value[index].style.maxHeight){
    collapses.value[index].style.maxHeight = null
  }else{
    removeCollapseShow()
    collapses.value[index].style.maxHeight = `${collapses.value[index].scrollHeight}px`
    collapses.value[index].style.maxHeight.lastElementChild.transform=`rotate(180deg)`
  }
}
function removeCollapseShow(){
  collapses.value.forEach((item)=>{
    item.style.maxHeight = null
  })
}
</script>

<template>
  <div class="accordion-collapse education-card flex flex-col gap-5" data-aos="zoom-in"  >
    <div class="collapse-container "  v-for="(item,index) in props.data" :key="index">
      <div class="button text-red-600 flex justify-between px-5 py-4" :style="`background:${item.color}`"
           @click="show(index)">
        <h3>{{ item.title }}</h3>
        <span><i class="fa-solid fa-angle-down transition duration-300 ease-linear"></i></span>
      </div>
      <div class="text  " ref="collapses">
        <p class="p-3">
          {{ item.text }}
          {{ item.text }}
          {{ item.text }}
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
  max-height:0;
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


</style>