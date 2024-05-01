<script setup lang="ts">
const props = defineProps(["data", "theme"])
const parent = ref(null)
const child = ref(null)
const showList = ref(false)

function show() {
  parent.value.classList.toggle('show')
  showList.value = !showList.value
}
</script>

<template>
  <li class="w-full  py-3 father navbar-mobile-item  relative mt-3 "
      :class="{ border : props.theme === 'parent' , theme:props.theme===true , borderNone:!props.data.sub}"
      ref="parent">
    <div class="flex  justify-between w-full">
      <nuxtLink :to="props.data.link">
        <span>{{ props.data.name }}</span>
      </nuxtLink>
      <div class="navbar-mobile-icon">
        <span v-if="props.data.sub" class="icon" @click="show"></span>
      </div>
    </div>
    <div class="sub-menu overflow-hidden" v-if="props.data.sub " ref="child">
      <ul class="nav w-full overflow-hidden">
        <transition-group name="transition">
          <navbar v-if="props.data.sub && showList" v-for="(item , index) in props.data.sub" :key="index" :data="item"
                  :theme="props.data.sub.length ? true : false"/>
        </transition-group>
      </ul>
    </div>
  </li>
</template>

<style scoped lang="scss">
.border {
  border: 0;
  border-bottom: 2px dashed rgba(0, 0, 0, 0.1);
}

.theme {
  color: #555555;
  padding: 16px 20px;
  margin-top: 10px;
}

.borderNone {
  border: none;
}

li > div > div > span.icon:after {
  position: absolute;
  content: "+";
  left: 0;
  font-size: 20px;
}

.sub-menu {
  transition: 0.5s;
}

li.show > div > div > span.icon:after {
  content: "-";
}

.transition-enter-active {
  transition: 0.5s ease;
}

.transition-leave-active {
  transition: 0.5s ease;
}

.transition-enter-from, .transition-leave-to {
  transform: translateX(20%);
  opacity: 0;
}
</style>