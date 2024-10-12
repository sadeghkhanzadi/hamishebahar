<script setup lang="js">
import {useDashboardIconsStore} from "~/store/dashboard/icons.js";

const editItemData = ref()

const store = useDashboardIconsStore()

await store.fetchingIcons()

const data = computed(() => store.getIcons)

async function insertIcon(obj) {
  await store.insertIcon(obj)
}

async function editData(obj) {
  await store.editIcon(obj)
}

function edit(item) {
  editItemData.value = {
    id: item.id,
    color: item.color,
    backgroundColor: item.backgroundColor,
    icon: item.icon,
    name: item.name,
  }
  store.showEditModal = true
}
</script>

<template>
  <section>
    <div class="container flex flex-col gap-y-5">
      <div class="add-icon flex justify-end">
        <button role="button" @click="store.showModal=true"
                class="bg-blue-500 text-sm text-white flex items-center gap-1.5 rounded px-3 py-1.5">
          ثبت آیکون جدید
        </button>
      </div>
      <div v-if="data&&data.length" class="grid grid-cols-12 gap-3">
        <div v-for="(item , index) in data" :key="index"
             class="flex flex-col items-center justify-center gap-0.5">
          <div class="bg-blue-100 rounded-lg pt-5 pb-2 px-2 flex flex-col gap-2">
            <figure class="flex h-full w-full flex-col items-center  justify-center">
                <span>
                  <i v-if="item.icon && item.icon.length"
                     :style="{color:item.color , backgroundColor: item.backgroundColor}"
                     :class="item.icon" class="fa-xl"></i>
                </span>
              <figcaption class="text-xs mt-4" v-if="item.name && item.name.length">{{ item.name }}</figcaption>
            </figure>
            <button class="text-xs bg-blue-500 text-white px-3 py-1 rounded" @click="edit(item)">ویرایش</button>
          </div>
        </div>
      </div>
    </div>
  </section>
  <transition name="show-icon">
    <dashboard-modal-icons @sendData="insertIcon" v-if="store.showModal" @close="store.showModal = false"/>
  </transition>
  <transition name="show-edit-icon">
    <dashboard-modal-edit-icons @sendData="editData" :edit="editItemData" v-if="store.showEditModal"
                                @close="store.showEditModal = false"/>
  </transition>
</template>

<style scoped lang="scss">
.show-icon-leave-active {
  animation: show-modal 0.35s ease reverse forwards;
}

.show-icon-enter-active {
  animation: show-modal 0.35s ease forwards;
}

.show-eidt-icon-leave-active {
  animation: show-modal 0.35s ease reverse forwards;
}

.show-eidt-icon-enter-active {
  animation: show-modal 0.35s ease forwards;
}

@keyframes show-modal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
