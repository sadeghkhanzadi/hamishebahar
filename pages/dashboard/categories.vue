<script setup lang="ts">
import {useDashboardCategoriesStore} from "~/store/dashboard/categories";

const deleteItemId = ref<number>(0)
const categoriesStore = useDashboardCategoriesStore()
await categoriesStore.fetchingCategories()
const categories = computed(() => categoriesStore.getCategories)


async function insertCategories(body: object) {
  await categoriesStore.insertCategories(body)
}

async function removeItem(id: string) {
  await categoriesStore.removeItem(id)
}

async function editItem(body: object) {
  await categoriesStore.editCategory(body)
}

function showAlert(id: string | number) {
  deleteItemId.value = id
  categoriesStore.showAlertModal = true
}

async function resultRemoveItem(result: boolean): void {
  if (result) {
    await categoriesStore.removeItem(deleteItemId.value)
  } else {
    categoriesStore.showAlertModal = false
  }
}
</script>

<template>
  <div class="categories flex flex-col gap-5">
    <div class="new-categories">
      <button class="text-sm text-white rounded bg-blue-500 px-3 py-2 float-left" @click="categoriesStore.showModal=true">دسته بندی
        جدید
      </button>
    </div>
    <div class="categories-content grid grid-cols-12 gap-2 md:gap-3">
      <dashboard-modal-categories
          v-if="categories&&categories.length"
          v-for="(item , index) in categories"
          :key="index"
          @delete="showAlert"
          @sendData="insertCategories"
          @edit="editItem"
          @cancel="refreshNuxtData"
          class="col-span-12 md:col-span-6 lg:col-span-3"
          :id="item.id"
          :status="item.is_active"
          :title="item.name"
          :comment="item.comment"
      />
    </div>
    <template v-if="categoriesStore.showModal">
      <transition-group>
        <div class="backdrop flex justify-center fixed bg-[rgba(0,0,0,0.15)] inset-0 w-full h-full"
             @click.self="categoriesStore.showModal = !categoriesStore.showModal">
          <div class="modal-body mt-[12vh]">
            <dashboard-modal-categories
                @sendData="insertCategories"
                :modal="true"/>
          </div>
        </div>
      </transition-group>
    </template>
  </div>
  <transition name="transition">
    <dashboard-modal-alert v-if="categoriesStore.showAlertModal" @result="resultRemoveItem"/>
  </transition>
</template>

<style scoped lang="scss">
.transition-leave-active {
  animation: showing 0.3s ease-in-out forwards reverse;
}

.transition-enter-active {
  animation: showing 0.3s ease-in-out forwards;
}

@keyframes showing {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
