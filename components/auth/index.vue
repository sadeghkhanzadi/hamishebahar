<script setup lang="ts">
const showAuth = ref(false)
</script>

<template>
  <div class="backdrop fixed w-full h-full backdrop-blur-[5px] inset-0 z-50 ">
    <div class="btn-close">
      <slot/>
    </div>
    <lazy-auth-signup v-if="showAuth " @showLogin="showAuth=false"/>
    <lazy-auth-login v-else @showSignup="showAuth=true"/>
  </div>
</template>

<style scoped lang="scss">
.backdrop {
  animation: backdrop 1s ease 1 forwards;
}

@keyframes backdrop {
  from {
    filter: opacity(0);
    background-color: transparent;
  }
  to {
    filter: opacity(1);
    background-color: rgba(255, 255, 255, 0.5);
  }
}
</style>