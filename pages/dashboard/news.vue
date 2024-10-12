<script setup lang="ts">
const showAddNews = ref(false)
const showEditNews = ref(false)
const apiBaseUrl = useState('apiBaseUrl').value
const router = useRouter()
const route = useRoute()
const cookie = useCookie('jwt')
const showAlert = ref(false)
const removeNewsId = ref()
const removeNewsTitle = ref()
const editNewsId = ref()
const news = ref([])
const pendingRequest = ref(false)
const {$date} = useNuxtApp()
getNews()
const tableData = ref({
  thead: [
    {
      name: 'ردیف'
    },
    {
      name: 'عنوان'
    },
    {
      name: 'توضیحات'
    },
    {
      name: 'تاریخ ثبت'
    },
    {
      name: 'تاریخ انقضا'
    },
    {
      name: 'تاریخ بروزرسانی'
    },
    {
      name: 'وضعیت'
    },
    {
      name: 'عملیات'
    },
  ],
})

async function getNews(title:null, startTime:null, endTime:null, size:null) {
  const {data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/news`, {
    headers: {
      Authorization: `${cookie.value}`
    },
    query: {
      title: title,
      startTime: startTime,
      endTime: endTime,
      size: size,
    },
    server:false
  })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    console.log(data.value)
    news.value = data.value.result
    console.log(news.value)
  }
}

const tableBodyDataContent = computed(() => news.value.content)

async function postNewEvent(information: object, theme) {
  pendingRequest.value = true
  const imgId = []
  information.medias.forEach(item => {
    imgId.push({id: item.id})
  })
  const json = {
    title: information.title,
    text: information.text,
    startDate: information.dateTime[0],
    endDate: information.dateTime[1],
    is_active: information.is_active,
    medias: imgId,
  }
  if (theme === 'create') {
    const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/news`, {
      method: 'POST',
      body: JSON.stringify(json),
      headers: {
        Authorization: `${cookie.value}`
      },
      server:false
    })
    if (error.value) {
      console.log(error.value)
    }
    if (status.value === 'success') {
      pendingRequest.value = false
      showAddNews.value = false
      getNews()
    }
  }
  if (theme === 'edit') {
    json.id = editNewsId.value
    const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/news/${editNewsId.value}`, {
      method: 'PUT',
      body: JSON.stringify(json),
      headers: {
        Authorization: `${cookie.value}`
      },
      server:false
    })
    if (error.value) {
      console.log(error.value)
    }
    if (status.value === 'success') {
      pendingRequest.value = false
      showEditNews.value = false
      getNews()
    }
  }
}

function showRemoveAlert(id: number | string, title: string) {
  showAlert.value = true
  removeNewsTitle.value = {name: title}
  removeNewsId.value = id
}

async function removeNews(id: number | string) {
  const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/news/${id.value}`, {
        method: 'DELETE',
        headers: {
          Authorization: `${cookie.value}`
        },
        server:false
      })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    removeNewsId.value = ""
    showAlert.value = false
    refreshPageData()
  }
}

function resultRemoveNews(result) {
  if (result) {
    removeNews(removeNewsId)
  } else {
    showAlert.value = false
  }
}

function editNews(id) {
  editNewsId.value = id
  showEditNews.value = true
}

function searchInNews(data) {
  getNews(data.title, data.endTime, data.startTime, data.size)
  let query = {
    ...route.query,
    size: data.size || 10,
    title: data.title || null,
    endTime: data.endTime || null,
    startTime: data.startTime || null
  }
  for (let i in query) {
    query[i] === null ? delete query[i] : ""
  }
  router.push({path: route.fullPath, query: query})
}

onMounted(() => {
  watch(() => showAddNews.value, () => {
    if (showAddNews.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
  watch(() => showEditNews.value, () => {
    if (showEditNews.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
})

function refreshPageData() {
  refreshNuxtData()
}
</script>

<template>
  <section class="flex flex-col gap-5">
    <section>
      <dashboard-search @search="searchInNews" theme="news"/>
    </section>
    <section>
      <div class="flex">
        <button @click="showAddNews = true" class="bg-blue-500 text-white px-3 py-1.5 rounded mr-auto add-news">ثبت خبر
          جدید
        </button>
      </div>
    </section>
    <section class="bg-white flex flex-col gap-y-3  p-5 rounded">
      <div><h3 class="font-bold">لیست همه خبر ها</h3></div>
      <table>
        <thead>
        <tr>
          <th v-for="(item , index) in tableData.thead" :key="index">{{ item.name }}</th>
        </tr>
        </thead>
        <tbody v-if="tableBodyDataContent ">
        <tr v-if="tableBodyDataContent.length" v-for="(item , index) in tableBodyDataContent" :key="index"
            :class="{'bg-red-100':item.is_deleted === true}">
          <td>{{ index + 1 }}</td>
          <td>{{ item.title }}</td>
          <td>{{ item.text.length > 30 ? item.text.substr(0, 30) + '...' : item.text }}</td>
          <td>{{ $date(item.startDate) }}</td>
          <td>{{ $date(item.endDate) }}</td>
          <td>{{ $date(item.updatedAt) }}</td>
          <td class="  rounded status"><span
              :class="` w-fit mx-auto px-3 py-1 rounded ${ item.is_active ? 'bg-blue-500' :'bg-red-500'}`">{{
              item.is_active === true ? 'فعال' : "غیرفعال"
            }}</span></td>
          <td class=" py-1 ">
            <button
                class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-blue-700 text-white bg-blue-500 inline-flex items-center justify-center rounded px-2 py-3">
              <i
                  class="fa-solid fa-eye fa-xs"/></button>
            <button
                @click="editNews(item.id)"
                class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-yellow-700 text-white bg-yellow-500 inline-flex items-center justify-center rounded px-2 py-3">
              <i
                  class="fa-solid fa-pen fa-xs"/></button>
            <button
                :disabled="item.is_deleted"
                :class="{'opacity-30':item.is_deleted , 'cursor-not-allowed' : item.is_deleted}"
                @click="showRemoveAlert(item.id , item.title)"
                class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-red-700 text-white bg-red-500 inline-flex items-center justify-center rounded px-2 py-3">
              <i
                  class="fa-solid fa-trash-alt fa-xs"/></button>
          </td>
        </tr>
        </tbody>
      </table>
    </section>
    <section class="">
      <dashboard-form-news
          @sendData="postNewEvent"
          @close="showAddNews = false"
          v-if="showAddNews"
          event="ثبت رویداد جدید"
          :create="true"
          :pending="pendingRequest"
      />
    </section>
    <section class="">
      <dashboard-form-news
          @sendData="postNewEvent"
          @close="showEditNews = false"
          v-if="showEditNews"
          event="ویرایش رویداد قبلی"
          :get="editNewsId"
          :edit="true"
          :pending="pendingRequest"
      />
    </section>
    <section class="">
      <dashboard-modal-alert
          v-if="showAlert"
          @result="resultRemoveNews"
          :data="removeNewsTitle"
      />
    </section>
  </section>
</template>

<style scoped lang="scss">
button.add-news {
  font-size: var(--text-md);
}


section > div > h3 {
  font-size: var(--title-xl);
}


table {
  width: 100%;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid rgba(0, 0, 0, 0.15);
  text-align: center;
  font-size: var(--text-lg);
  font-weight: var(--font-medium);
  padding-top: 5px;
  padding-bottom: 5px;
}

th {
  font-size: var(--text-lg);
  font-weight: var(--font-bold);
}

.status {
  color: #fff;

  span {
    font-size: var(--text-md);
    display: flex;
    justify-content: center;
  }
}
</style>
