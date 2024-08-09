<script setup lang="ts">

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
      <div class="nav flex gap-7">
        <div class="notification py-1.5 px-2.5 rounded-full">
          <div class="icon ">
          <span class="">
            <i class="fa-regular fa-xl fa-bell"/>
          </span>
          </div>
        </div>
        <div class="user py-1.5 px-2.5 rounded-full">
          <div class="icon">
          <span>
            <i class="fa-regular  fa-user  fa-user"/>
          </span>
          </div>
        </div>
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