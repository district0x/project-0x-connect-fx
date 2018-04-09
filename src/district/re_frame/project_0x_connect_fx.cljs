(ns district.re-frame.project-0x-connect-fx
  (:require [cljs.spec.alpha :as s]
            [re-frame.core :as re-frame]
            [cljs-0x-connect.http-client :as connect-http]
            [cljs-0x-connect.ws-orderbook :as connect-ws]))

;; TODO: spec parameters

(defn- http [{:keys [http-client fn args on-success on-error]}]
  (let [m (-> (ns-publics 'cljs-0x-connect.http-client)
                   ((-> fn name symbol)))
             [request opts] args]
         (js-invoke (m http-client {:request request :opts opts})
                    "then"
                    #(re-frame/dispatch (conj on-success %))
                    #(re-frame/dispatch (conj on-error %)))))

(defn- ws [{:keys [ws-orderbook-channel fn args on-snapshot on-update on-error on-close]}]
  (let [handler (connect-ws/create-channel-handler {:on-snapshot on-snapshot
                                                    :on-update on-update
                                                    :on-error on-error
                                                    :on-close on-close})
        m (-> (ns-publics 'cljs-0x-connect.ws-orderbook)
              ((-> fn name symbol)))]
    (m ws-orderbook-channel {:opts (first args)
                             :handler handler})))

(re-frame/reg-fx
 :0x-connect
 (fn [calls]
   (doseq [{:keys [:http-client :fn :args :on-success :on-error
                   :ws-orderbook-channel :on-close :on-error :on-snapshot :on-update] :as params} calls]
     (if http-client
       (http {:fn fn :args args :http-client http-client :on-success on-success :on-error on-error})
       (ws {:ws-orderbook-channel ws-orderbook-channel :fn fn :args args :on-snapshot on-snapshot :on-update on-update :on-erro on-error :on-close on-close})))))

