<script setup lang="ts">
const props = defineProps(['data'])
</script>

<template>
  <div class="social-media">
    <ul class="flex gap-3 items-center justify-center ">
      <li class="telegram px-2 py-4 rounded-full">
        <nuxtLink class="flex justify-center items-center" to="data.socialMedia.telegram"><i
            class="fa-brands fa-telegram-plane fa-lg"/></nuxtLink>
      </li>
      <li class="instagram px-2 py-4 rounded-full">
        <nuxtLink class="flex justify-center items-center" to="data.socialMedia.instagram"><i
            class="fa-brands fa-instagram fa-lg"/></nuxtLink>
      </li>
      <li class="linkedin px-2 py-4 rounded-full">
        <nuxtLink class="flex justify-center items-center" to="data.socialMedia.linkedin"><i
            class="fa-brands fa-linkedin-in fa-lg"/></nuxtLink>
      </li>
    </ul>
  </div>
</template>

<style scoped lang="scss">
.social-media {
  color: rgb(167, 167, 167);

  li {
    transition: 0.3s;
    border: 1px solid rgba(0, 0, 0, 0.1);

    &:hover {
      background: #1ab69d;
      color: #FFFFFF;
    }
  }
}
</style>