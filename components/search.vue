<script setup lang="ts">
const router = useRouter()

const emit = defineEmits(['closeSearch'])
const searchInput = ref()
// const store =useProductsStore()
function searchProducts(){
  // router.push({path:'/products' , query:{title:searchInput.value}})
  emit('closeSearch')
}
</script>

<template>
<teleport to="body">
  <div class="search-box backdrop-modal" @click.self="emit('closeSearch')">
    <div class="form flex items-center w-full mt-[15vh] relative ">
      <input type="text" v-model="searchInput" class=" w-11/12 sm:w-2/3 mx-auto block rounded-full py-3 outline-0 px-3 md:px-5 focus:border-2 focus:shadow-sm focus:shadow-[#AE7BB6] focus:border-[#AE7BB6]  " name="" id="" placeholder="دنبال چی میگردی ... ؟">
      <button class="absolute bottom-2.5 left-[7%] sm:left-[19%] lg:left-[17.5%] " @click="searchProducts"><icons-search/></button>
    </div>
  </div>
</teleport>
</template>

<style scoped lang="scss">
.search-box{
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.4);
  width: 100%;
  height: 100%;
  z-index: 20;
  backdrop-filter: blur(5px);
}
input::placeholder{
  font-size: var(--text-2xl);
  @media (max-width: 768px){
    font-size: var(--text-xl);
  }
}

</style>
