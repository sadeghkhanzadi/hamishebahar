<script setup >
import {useDashboardMedia} from "~/store/dashboard/mediaSelector.js";
const store = useDashboardMedia()
const showFilterMedia = ref(false)
const editCard = ref(false)
const addMedia = ref(false)
const showAlertRemove = ref(false)
const alertData = ref("")
const dataCard = ref()
const route = useRoute()
const router = useRouter()
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
store.fetchDashboardMediaData()
const mediaData = computed(()=>store.getMedia.filter(item => item.is_active === true))
const filters = [
  {
    name: "همه فایل های رسانه",
    sort: "all"
  },
  {
    name: "تصاویر",
    sort: "images"
  },
  {
    name: "صوت ها",
    sort: "sounds"
  },
  {
    name: "ویدیو ها",
    sort: "videos"
  },
  {
    name: "سندها",
    sort: "texts"
  },
  {
    name: "صفحه های گسترده",
    sort: "sheets"
  },
  {
    name: "بایگانی ها",
    sort: "storage"
  },
  {
    name: "مال خودم",
    sort: "me"
  },

]

function showEditeCard(data ) {
  dataCard.value = {data:data}
  editCard.value = !editCard.value
  console.log(dataCard.value)
}
function  closeFilterMedia(){
  setTimeout(()=>(showFilterMedia.value = false),100)
}
 async function editMedia(media ){
  const formData = new FormData()
   formData.append('name' , media.name)
   formData.append('file' , media.image)
   formData.append('states' , media.status)
  console.log(media)
  const {data , status , error} = await useFetch(`${apiBaseUrl}/api/v1/media/${dataCard.value.data.id}` , {
    method:"PUT",
    headers:{
      Authorization:`${cookie.value}`
    },
    body:formData
  })
   if (status.value === "success"){
     console.log( 'فایل با موفقیت ویرایش شد ', data.value)
   }
   if (error.value){
     console.log(error.value)
   }
}
async function insertMedia(media){
  const formData = new FormData()
  formData.append('file' , media.image)
  formData.append('name' , media.name)
  formData.append('states' , media.status)
  const {status , error} = await useFetch(`${apiBaseUrl}/api/v1/media`,{
    method:'POST',
    headers:{
      Authorization:`${cookie.value}`
    },
    body:formData
  })
  if (status.value === 'success'){
    addMedia.value = false
    store.fetchDashboardMediaData()
  }
  if (error.value){
    console.log(error.value)
  }
}
async function deleteMedia(id){
  const {data , status , error} = await useAsyncData('removeData' , ()=> $fetch(`${apiBaseUrl}/api/v1/media/${id}`,{
    method:'DELETE',
    headers:{
      Authorization:`${cookie.value}`
    },
  }))
  if (status.value === 'success'){
    console.log(data.value)
    showAlertRemove.value = false
    store.fetchDashboardMediaData()
  }
  if (error.value){
    console.log(error.value)
  }
}
function resultProcess (result){
  if (result){
    deleteMedia(alertData.value)
    console.log(alertData.value)
  }
  else {
    showAlertRemove.value = false
  }
  console.log(result)
}
function activeAlert(data){
  alertData.value = data
  showAlertRemove.value = true
}
</script>

<template>
  <div>
    <div class="header flex items-center  justify-between">
      <section>
        <h1 class="title text-2xl font-bold">رسانه ها</h1>
      </section>
      <section class="flex gap-10 items-center">
        <div class="filters relative w-fit   ">
          <div @click="showFilterMedia = !showFilterMedia"
               class="filter-title hover:cursor-pointer flex gap-3 justify-between items-center  px-3 text-center border py-1.5 rounded-lg bg-white text-sm">
            <i class="fa-solid fa-sm fa-sort-amount-down"/>
            <h3>فیلتر بر اساس</h3>
            <i class="fa-solid fa-sm fa-angle-down"/>
          </div>
          <div v-if="showFilterMedia" class="absolute filters-body rounded-sm overflow-hidden w-full mt-3 z-10">
            <ul class="bg-white w-full text-sm  ">
              <li class="py-2 px-3 hover:bg-gray-50" @click="closeFilterMedia"  v-for="(item , index) in filters"
                  :key="index">
                <nuxt-link :to="{query:{sort:item.sort}}">{{ item.name }}</nuxt-link>
              </li>
            </ul>
          </div>
        </div>
        <div>
          <div class="add-media px-10  py-1.5 rounded-lg ">
            <div class="flex gap-3 items-center hover:cursor-pointer " @click="addMedia=true">
              <i class="fa-solid fa-plus fa-md"/>
              <h3 class="mx-auto">افزودن رسانه</h3>
            </div>
          </div>
        </div>
      </section>
    </div>
    <main>
      <article>
        <div class="mt-10">
          <section class="media-card grid grid-cols-5 gap-5" v-if="mediaData">
            <lazy-dashboard-media @edite-card="showEditeCard" class=" col-span-4 md:col-span-2 lg:col-span-1" v-for="(item , index) in mediaData" @remove-card="activeAlert" :key="index" :data="item"/>
          </section>
          <section class="edite-media-modal">
            <transition name="modal">
              <lazy-dashboard-media-edite @close="editCard = !editCard" v-if="editCard" :data="dataCard" @sendMedia="editMedia" />
            </transition>
          </section>
          <section class="add-media-modal">
            <transition name="modal">
              <lazy-dashboard-media-edite theme="addMedia" @close="addMedia = !addMedia" v-if="addMedia" @sendMedia="insertMedia" />
            </transition>
          </section>
          <section class="remove-media-modal">
              <transition name="modal">
                <lazy-dashboard-modal-alert @result="resultProcess" v-if="showAlertRemove" :data="alertData"  />
              </transition>
          </section>
        </div>
      </article>
    </main>
  </div>
</template>

<style scoped lang="scss">
h1{
  font-size: var(--text-2xl);

}
.filters{
  h3{
    font-size: var(--text-md);

  }
}
.filters-body {
  box-shadow: 2px 2px 5px 1px rgba(0, 0, 0, 0.5);
}

.add-media {
  background: #3498db;
  color: white;
  position: relative;
  padding-right: 25px;
  font-size: var(--text-md);
}



</style>
