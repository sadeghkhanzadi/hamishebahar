<script setup lang="ts">
const cookie = useCookie('jwt' ,{
  maxAge:24*3600*60
} )
</script>

<template>
  <div class="">
    <div class="navbar flex justify-between items-center px-10 py-3">
      <div class="navbar-brand flex items-center ">
        <figure class="rounded-full overflow-hidden flex justify-center items-center w-10 ">
          <img src="/logo/logo.png" alt="همیشه بهار ">
        </figure>
        <h1 class="mr-2.5">همیشه بهار</h1>
      </div>
      <div class="nav flex gap-5 items-center">
        <button class="notification py-1.5 px-2.5 rounded-full">
          <span class="">
            <i class="fa-regular fa-xl fa-bell"/>
          </span>
        </button>
        <button class="logout py-1.5 px-2.5 rounded-full">
          <span class="">
            <i class="fa-solid hover:text-red-500 fa-xl fa-right-from-bracket"/>
          </span>
        </button>
        <button class="user py-1.5 px-2.5 rounded-full">
          <span>
            <i class="fa-regular  fa-user  fa-user"/>
          </span>
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.navbar {
  .user {
    background: #3498db;
  }

  .notification {
    &:hover {
      background: rgba(0, 0, 0, 0.1);
    }
  }

}
</style>