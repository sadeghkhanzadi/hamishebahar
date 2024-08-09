<script setup lang="ts">
const {data, title, theme} = defineProps(['data', 'title', 'theme'])
const apiBaseUrl = useState('apiBaseUrl').value
const emit = defineEmits(['close'])
// Import Swiper Vue.js components
import {Swiper, SwiperSlide} from 'swiper/vue';

// Import Swiper styles
import 'swiper/css';

import 'swiper/css/pagination';


// import required modules
import {Pagination} from 'swiper/modules';

const modules = [Pagination]

/* خب این فرم هم برای  معلم ها و هم برای دانش آموزان مورد
استفاده قرار میگیره که چون یه سری از قسمت ها Api
 فرق داره و یه سری از جا ها api جدید داریم پس با props
 مدیریت کردیمش و اگر v-if بود دانش آموزان میشه در غیر این صورت معلمان میشه  */
</script>

<template>
  <section>
    <div class="backdrop overflow-auto fixed inset-0 w-full h-full" @click.self="emit('close')">
      <div
          class="modal-content p-5 mt-[10vh] mb-[3vh] mx-auto bg-white w-11/12 md:w-[80%] lg:w-[50%] max-w-screen-xl z-10 rounded  shadow-lg">
        <div class="title text-center mb-8">
          <h5 v-if="title && title.length">{{ title }}</h5>
        </div>
        <swiper
            v-if="data.documentFiles && data.documentFiles.length"
            :slidesPerView="1"
            :spaceBetween="10"
            :breakpoints="{
            '640': {
              slidesPerView: 2,
              spaceBetween: 20,
            },
            '768': {
              slidesPerView: 4,
              spaceBetween: 40,
            },
            '1024': {
              slidesPerView: 5,
              spaceBetween: 50,
            },
          }"
            class="mySwiper"
        >
          <swiper-slide v-for="(item , index) in data.documentFiles " :key="index">
            <div class="image">
              <img v-if="item.pathFile" :src="`${apiBaseUrl}/api/v1/view/media/download/${item.pathFile}`"
                   :alt="item.name">
              <span v-if="item.name">{{ item.name }}</span>
            </div>
          </swiper-slide>
        </swiper>
        <div class="content my-5">
          <div class="grid grid-cols-2 gap-2">
            <div class="user-info col-span-1">
              <h5>نام :</h5>
              <span v-if="theme==='student'">{{ data.first_name }}</span>
              <span v-else>{{ data.firstName }}</span>
            </div>
            <div class="user-info col-span-1">
              <h5>نام خانوادگی :</h5>
              <span v-if="theme==='student'">{{ data.last_name }}</span>
              <span v-else>{{ data.lastName }}</span>
            </div>
          </div>
          <div class="grid grid-cols-2 gap-2">
            <div class="user-info col-span-1" v-if="theme==='student'">
              <h5> کد دانش آموزی :</h5>
              <span>{{ data.studentCode }}</span>
            </div>
            <div class="user-info col-span-1" v-else>
              <h5>شماره پرسنلی :</h5>
              <span>{{ data.teacherCode }}</span>
            </div>
            <div class="user-info col-span-1">
              <h5>کدملی :</h5>
              <span>{{ data.nationalCode }}</span>
            </div>
          </div>
          <div class="grid grid-cols-2 gap-2">
            <div class="user-info col-span-1">
              <h5>شماره تماس : </h5>
              <a :href="`tel:${data.phoneNumber}`"><span>{{data.phoneNumber}}</span></a>
            </div>
            <div class="user-info col-span-1" v-if="theme==='student'">
              <h5>شماره تماس اضطراری: </h5>
              <a :href="`tel:${data.emergencyPhoneNumber}`"><span>{{ data.emergencyPhoneNumber }}</span></a>
            </div>

          </div>
          <div class="grid grid-cols-2 gap-2" v-if="theme != 'student'">
            <div class="user-info col-span-1">
              <h5>سابقه شغلی :</h5>
              <span>{{ data.workExperience }} سال</span>
            </div>
            <div class="user-info col-span-1">
              <h5>عنوان شغلی :</h5>
              <span>{{ data.jobTitle }}</span>
            </div>
          </div>

          <div v-if="theme === 'student' ">
            <div class="user-info">
              <h5>ملاضحات :</h5>
              <span v-if="data.importantDetails">{{ data.importantDetails}}</span>
            </div>
            <div class="user-info">
              <h5>بیماری های خاص :</h5>
              <span v-if="data.specialDisease">{{ data.specialDisease}}</span>
            </div>
          </div>

          <div class="grid grid-cols-2 ">
            <div class="user-info col-span-1 ">
              <h5>وضعیت :</h5>
              <span class="active px-2  rounded"
                    :class="{'bg-red-500':data.is_active === false , 'bg-blue-500':data.is_active}">{{
                  data.is_active ? 'فعال' : 'غیر فعال'
                }}</span>
            </div>
            <div class="user-info col-span-1">
              <h5>سن :</h5>
              <span v-if="data.studentAge">{{ data.studentAge}} سال</span>
              <span v-else>{{data.age}} سال</span>
            </div>
          </div>
        </div>
        <div>
          <button class="w-full text-center bg-red-500 rounded py-1" @click="emit('close')">بستن</button>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.image{
  span{
    font-size: var(--text-sm);
  }
}
.backdrop {
  background: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(3px);

  .title {
    h5 {
      font-size: var(--title-2xl);
      font-weight: var(--font-bold);
    }
  }

  .modal-content {
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
    overflow: auto;
  }

  .content {
    .user-info {
      display: flex;
      gap: 6px;
      line-height: var(--landing-xl);

      h5 {
        font-size: var(--title-md);
        font-weight: var(--font-bold);
      }

      span {
        font-size: var(--text-md);
        color: #333;
      }

      span.active {
        color: white;
        font-size: var(--text-md);
      }
    }
  }

  button {
    font-size: var(--text-lg);
    color: #fff;
  }
}

</style>
