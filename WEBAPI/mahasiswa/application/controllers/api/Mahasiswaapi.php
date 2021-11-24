<?php

defined('BASEPATH') or exit('No direct script access allowed');

// This can be removed if you use __autoload() in config.php OR use Modular Extensions
/** @noinspection PhpIncludeInspection */
require APPPATH . '/libraries/REST_Controller.php';

// use namespace
use Restserver\Libraries\REST_Controller;

class Mahasiswaapi extends REST_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->helper('url');
        $this->load->database();
        $this->load->model('Mahasiswa_model', 'mhs');
    }

    public function index_get()
    {
        $this->response("Api for crud", 200);
    }

    public function tambahdata_post()
    {
        $data = file_get_contents("php://input");
        $dec_data = json_decode($data);

        $data_mahasiswa = array(
            'nama_siswa' => $dec_data->nama_siswa,
            'nim_siswa' => $dec_data->nim_siswa,
            'alamat_siswa' => $dec_data->alamat_siswa,
            'phone_siswa' => $dec_data->phone_siswa
        );

        $signup = $this->db->insert('siswa', $data_mahasiswa);
        if ($signup) {
            $message = array(
                'code' => '200',
                'status' => 'success',
                'data' => 'Tambah Data Berhasil Di Simpan'
            );
            $this->response($message, 200);
        } else {
            $message = array(
                'code' => '201',
                'message' => 'failed',
                'data' => ''
            );
            $this->response($message, 201);
        }
    }

    public function lihatdata_get()
    {
        $home = $this->db->get('siswa')->result_array();
        $message = array(
            'code' => '200',
            'home' => $home,
            'status' => 'success'
        );
        $this->response($message, 200);
    }

    function edit_mahasiswa_post()
    {

        $data = file_get_contents("php://input");
        $dec_data = json_decode($data);
        $dataitem = array(
            'nama_siswa' => $dec_data->nama_siswa,
            'nim_siswa' => $dec_data->nim_siswa,
            'alamat_siswa' => $dec_data->alamat_siswa,
            'phone_siswa' => $dec_data->phone_siswa

        );
        $cekdata = $this->mhs->edititem($dataitem, $dec_data->id_siswa);
        if ($cekdata) {
            $message = array(
                'code' => '200',
                'status' => 'success'
            );
            $this->response($message, 200);
        } else {
            $message = array(
                'code' => '200',
                'status' => 'failed'
            );
            $this->response($message, 200);
        }
    }

    function delete_mahasiswa_post()
    {

        $data = file_get_contents("php://input");
        $dec_data = json_decode($data);
        $cek_login = $this->mhs->deleteitem($dec_data->id_siswa);

        if ($cek_login) {
            $message = array(
                'code' => '200',
                'status' => 'success'
            );
            $this->response($message, 200);
        } else {
            $message = array(
                'code' => '200',
                'status' => 'failed'
            );
            $this->response($message, 200);
        }
    }
}
