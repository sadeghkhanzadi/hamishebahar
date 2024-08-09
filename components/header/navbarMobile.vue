<script setup lang="ts">
const emit = defineEmits(["closeNavbarMobile" , "showLogin"])
const props =defineProps(['data'])


</script>

<template>
  <div class="backdrop z-10" @click.self="emit('closeNavbarMobile')">
    <div class="navbar-mobile flex w-full relative md:w-1/2 lg:w-1/3 px-5 z-10 ">
      <nav class="w-full z-10">
        <div class="navbar-brand py-7 flex items-center">
          <nuxt-link to="/">همیشه بهار</nuxt-link>
        </div>
        <ul class="nav w-full ">
            <navbar v-for="(item , index) in data" :key="index" :data="item" theme="parent"/>
            <li class="my-3">
              <button @click="emit('showLogin')"> ورود | ثبت نام  </button>
            </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<style scoped lang="scss">
.backdrop {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(5px);

  .navbar-mobile {
    background-color: white;
    height: 100%;
    overflow: scroll;

    &::-webkit-scrollbar {
      display: none;
    }
  }
}

li:last-child {
  border: 0;
}

</style>
