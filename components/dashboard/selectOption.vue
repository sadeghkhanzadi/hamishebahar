<script setup lang="ts">
const {data, currentItem} = defineProps({
  data: Array,
  currentItem:{
    type:Object,
    default:""
  }
})
const emit = defineEmits(['sendData'])
const showOptions = ref(false)
const searchInput = ref(null)
const searchResult = ref("")
const selectItem = ref(currentItem)


function show() {
  showOptions.value = !showOptions.value
}

function selected(item) {
  console.log(item)
  selectItem.value = item
  emit('sendData', item.id.toString())
  showOptions.value = false
}

function search() {
  searchResult.value = data.filter(item => item.name.includes(searchInput.value.value.trim()))
}

onMounted(() => {
  document.body.addEventListener('click', (e) => {
    if (showOptions.value && e.target.id == 'backdrop-for-close-select-option') {
      showOptions.value = false
    }
  })
})

</script>

<template>
  <div>

    <div class="z-[1] relative">
      <div @click="show" class="relative z-[1] border border-[rgba(0,0,0,.1)] rounded  cursor-pointer">
        <p class=" z-0 py-1.5 px-3 w-full bg-white text-sm outline-0 select-none">
        <span v-if="selectItem && Object.keys(selectItem).length" class="flex items-center justify-between">
          <span>{{ selectItem.name }}</span>
          <span class=" bg-blue-100 py-1 px-2 flex items-center"><i :class="`${selectItem.icon} text-xl`"
                                                                    :style="{backgroundColor:selectItem.backgroundColor , color:selectItem.color}"/></span>
        </span>
          <span v-else>یک مورد را انتخاب کنید</span>
        </p>
      </div>
      <div class="relative parent-select-content">
        <div v-if="showOptions"
             class=" absolute w-full bg-white top-0 right-0 select-content border border-[rgba(0,0,0,.1)] rounded mt-0.5">
          <ul class="w-full">
            <template v-if="data && data.length">
              <li class="w-full py-1.5 px-3"><input @input="search" searchInput ref="searchInput" type="text"
                                                    class="w-full h-full outline-0 placeholder:text-sm"
                                                    placeholder="جستجو"></li>
              <li v-if="searchResult && searchResult.length" @click="selected(item)"
                  v-for="(item , index) in searchResult" :key="index"
                  class="w-full cursor-pointer py-2 px-3 text-sm text-gray-800 my-1.5 last:mb-0 first:mt-0 hover:bg-green-100">
                <div class="flex justify-between items-center ">
                  <span>{{ item.name }}</span>
                  <span class=" bg-blue-100 py-1 px-2 flex items-center"><i :class="`${item.icon} text-xl`"
                                                                            :style="{backgroundColor:item.backgroundColor , color:item.color}"/></span>
                </div>
              </li>
              <!-- for search in result-->
              <li v-else @click="selected(item)" v-for="(item , index) in data" :key="`item-${index}`"
                  class="w-full cursor-pointer py-2 px-3 text-sm text-gray-800 my-1.5 last:mb-0 first:mt-0 hover:bg-green-100">
                <div class="flex justify-between items-center ">
                  <span>{{ item.name }}</span>
                  <span class=" bg-blue-100 py-1 px-2 flex items-center"><i :class="`${item.icon} text-2xl`"
                                                                            :style="{backgroundColor:item.backgroundColor , color:item.color}"/></span>
                </div>
              </li>
            </template>
          </ul>
        </div>
      </div>
    </div>
    <div v-if="showOptions" class="fixed inset-0 w-full h-full z-0" id="backdrop-for-close-select-option"></div>
  </div>
</template>
<style scoped lang="scss">
.select-content {
  max-height: 150px;
  overflow-y: auto;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

/* width */
::-webkit-scrollbar {
  width: 5px;
}

/* Track */
::-webkit-scrollbar-track {
  background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #777;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555;
}
</style>
