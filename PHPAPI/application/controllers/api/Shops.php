<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . 'libraries/REST_Controller.php';

Class Shops extends REST_Controller {

    public function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->model("restmodel/Shop_model");
    }

    function create_response($data, $message) {
        $response = array('data' => $data, 'message' => $message);
        echo json_encode($response);
    }

//id`, `shop_name`, `address_one`, `address_two`, `address2`, `lat`, `lon
    public function create_post() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $user_register_array = array(
            'shop_name' => $incoming_json->sn,
            'address_one' => $incoming_json->a1,
            'address_two' => $incoming_json->a2,
            'address2' => $incoming_json->a3,
            'lat' => $incoming_json->lat,
            'lon' => $incoming_json->lon
        );

        if (isset($incoming_json->created_by)) {
            $user_register_array['created_by'] = $incoming_json->created_by;
        }

        $result = $this->Shop_model->create('tbl_shops', $user_register_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Shop Creation Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Shop reation failed"), 422);
        }
    }

    public function update_put() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $user_register_array = array(
            'shop_name' => $incoming_json->sn,
            'address_one' => $incoming_json->a1,
            'address_two' => $incoming_json->a2,
            'address2' => $incoming_json->a3,
            'lat' => $incoming_json->lat,
            'lon' => $incoming_json->lon,
        );

        $where = array("id" => $incoming_json->sid);

        $result = $this->Shop_model->update('tbl_shops', $user_register_array, $where);
//        var_dump($result);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Shop Update Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Shop Update failed"), 422);
        }
    }

    public function load_post() {
//        $incoming_json = json_decode(file_get_contents("php://input"));
        $result = $this->Shop_model->get_shop_information();
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "ShopInformation"), 200);
        } else {
            $this->response($this->create_response(array(), "Failed to load shop information"), 422);
        }
    }

    public function remove_delete() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $where = array("id" => $incoming_json->sid);
        $result = $this->Shop_model->remove('tbl_shops', $where);
//        var_dump($result);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Shop Delete Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Shop Delete failed"), 422);
        }
    }

}
