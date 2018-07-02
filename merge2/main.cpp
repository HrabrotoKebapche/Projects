

#include<stdlib.h>

#include<stdio.h>

using namespace std;



void merge(int arr[], int l, int m, int r) {

    int i, j, k;

                int n= m - l + 1;

                int n2 = r - m;

                int L[n];

                int R[n2];





                for (i = 0; i < n; i++) {

                                L[i] = arr[l + i];

                }



                for ( j = 0; j < n2; j++) {

                                R[j] = arr[m + j + 1];

                }



                i = 0;

                j = 0;

                k = l;

                while (i < n && j < n2) {

                                if (L[i] <= R[j]) {

                                                arr[k] = L[i];

                                                i++;



                                }

                                else {

                                                arr[k] = R[j];

                                                j++;



                                }

                                k++;

                }





                while (i < n)

    {

        arr[k] = L[i];

        i++;

        k++;

    }



    while (j < n2)

    {

        arr[k] = R[j];

        j++;

        k++;

    }





}





void mergeSort(int arr[], int l, int r) {



                if (l < r) {

                                int mid = l+(r-l) / 2;

                                mergeSort(arr, l, mid);

                                mergeSort(arr, mid + 1, r);



                                merge(arr, l, mid, r);

                }

}



int main()

{

                int arr[] = { 12, 11, 13, 5, 6, 7 };

                int arr_size = sizeof(arr) / sizeof(arr[0]);

                mergeSort(arr, 0, arr_size-1);

                for (int i = 0; i < arr_size; i++) {

                                printf("%d  ",arr[i]);

                }

}
