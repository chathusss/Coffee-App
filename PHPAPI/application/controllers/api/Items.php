<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
defined('BASEPATH') OR exit('No direct script access allowed');
require APPPATH . 'libraries/REST_Controller.php';

Class Items extends REST_Controller {

    public function __construct($config = 'rest') {
        parent::__construct($config);
        $this->load->model("restmodel/Item_model");
    }

    function create_response($data, $message) {
        $response = array('data' => $data, 'message' => $message);
        echo json_encode($response);
    }

//`menu_id`, `shop_id`, `menu_name`, `is_valid`
    public function create_post() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $user_register_array = array(
            'description' => $incoming_json->description,
            'price' => $incoming_json->price
        );

        $result = $this->Item_model->create('tbl_food_item', $user_register_array);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Item Creation Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Item reation failed"), 422);
        }
    }

    public function update_put() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $update_menu_array = array(
            'description' => $incoming_json->description,
            'price' => $incoming_json->price
        );

        $where = array("item_id" => $incoming_json->item_id);
        $result = $this->Item_model->update('tbl_food_item', $update_menu_array, $where);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Item Update Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Item Update failed"), 422);
        }
    }

    public function load_get() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $result = $this->Item_model->get_Item_information($incoming_json->mid);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Item INFO"), 200);
        } else {
            $this->response($this->create_response(array(), "Failed to load Item information"), 422);
        }
    }

    public function remove_delete() {
        $incoming_json = json_decode(file_get_contents("php://input"));
        $where = array("item_id" => $incoming_json->item_id);
        $result = $this->Item_model->remove('tbl_food_item', $where);
//        var_dump($result);
        if ($result != NULL && $result != FALSE) {
            $this->response($this->create_response($result, "Item Delete Successful"), 200);
        } else {
            $this->response($this->create_response(array(), "Item Delete failed"), 422);
        }
    }

}
