<script setup >
import {useDashboardMedia} from "~/store/dashboard";

const emit = defineEmits(['close', 'selectFile'])
const {public: {baseUrl}} = useRuntimeConfig()
const mediaCards = ref(null)
const store = useDashboardMedia()
store.fetchDashboardData()
const getMedia = computed(() => store.getDashboardMedia.filter(item => item.is_active === true))
const medias = ref([])


function selectFile(index, item) {
  const obj = {}
  obj.id = item.id
  obj.name=item.name
  if (medias.value.length){
    if (medias.value.find(item => item.id === obj.id)){
      mediaCards.value[index].classList.remove('select')
      removeMedias(item.id)
    }
    else {
      medias.value.push(obj)
      mediaCards.value[index].classList.add('select')
    }
  }else {
    medias.value.push(obj)
    mediaCards.value[index].classList.add('select')

  }
}
function removeMedias(id){
  medias.value= medias.value.filter(item => item.id !== medias.value.find(item => item.id === id).id)
}
function handleMedia(){
  emit('selectFile' , medias.value)
  emit('close')
}
</script>

<template>
  <div @click.self="emit('close')"
       class="backdrop-media fixed backdrop-blur-sm bg-[rgba(0,0,0,0.2)] overflow-scroll top-0 left-0 w-full h-full flex justify-center items-start">
    <div class="media-content  w-2/3 bg-white p-5 mt-[10vh] rounded text-black overflow-auto" v-if="getMedia">
      <div class="modal-body grid grid-cols-12 gap-5">
        <div class="card-media relative col-span-6 md:col-span-4 lg:col-span-3 rounded overflow-hidden cursor-pointer"
             v-for="(item , index) in getMedia" :key="index" @click="selectFile(index , item)" ref="mediaCards">
          <div><img :src="`${baseUrl}/api/v1/view/media/download/${item.pathFile}`" :alt="item.name"></div>
          <div class="title"><h4>{{ item.name }}</h4></div>
        </div>
      </div>
      <div class="w-full flex flex-wrap gap-2 py-2 px-3 mt-5  bg-blue-400 text-white rounded hover:bg-blue-500 transition duration-200 ease-linear cursor-pointer ">
        <button class="w-full" @click.prevent="handleMedia">ثبت</button>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.media-content{
  max-height: calc(100vh - 30vh);
  overflow: auto;
}
.select {
  &:after {
    content: "";
    position: absolute;
    inset: 0;
    width: 100%;
    height: 100%;
    background: rgba(52, 152, 219, 0.3);
    z-index: 3;
  }
}
</style>
